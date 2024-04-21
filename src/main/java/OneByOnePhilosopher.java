public class OneByOnePhilosopher implements Runnable {
    boolean[] forks;
    int leftFork;
    int rightFork;
    String name;
    int number;

    public OneByOnePhilosopher(String name, int number, int leftFork, int rightFork, boolean[] forks) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.name = name;
        this.number = number;
        this.forks = forks;
    }


    @Override
    public void run() {
        try {
            while (true) {
                think(name);
                if (number % 2 == 1) {
                    synchronized (forks) {
                        waitForRightFork(rightFork);
                        System.out.println(name + " ma prawy widelec");
                    }
                    synchronized (forks) {
                        waitForLeftFork(leftFork);
                        System.out.println(name + "ma lewy widelec");

                    }
                } else {
                    synchronized (forks) {
                        waitForLeftFork(leftFork);
                        System.out.println(name + " ma lewy widelec");
                    }
                    synchronized (forks) {
                        waitForRightFork(rightFork);
                        System.out.println(name + " ma prawy widelec");
                    }
                }
                eat();
                System.out.println(name + " skonczył jesc i zwalnia widelce");
                synchronized (forks) {
                    releaseLeftForks(leftFork);
                }
                synchronized (forks) {
                    releaseRightForks(rightFork);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public void eat() throws InterruptedException {
        System.out.println(name + " je spaget");
        Thread.sleep(1000);
    }

    public void waitForLeftFork(int leftFork) throws InterruptedException {
        while (!forks[leftFork]) {
            System.out.println(name + "czeka na lewy widelec");
            forks.wait();
        }
        forks[leftFork] = false;
    }

    public void waitForRightFork(int rightFork) throws InterruptedException {
        while (!forks[rightFork]) {
            System.out.println(name + "czeka na prawy widelec");
            forks.wait();
        }
        forks[rightFork] = false;
    }

    public void releaseLeftForks(int leftFork) {
        forks[leftFork] = true;
        forks.notifyAll();
    }

    public void releaseRightForks(int rightForkFork) {
        forks[rightFork] = true;
        forks.notifyAll();
    }

    public void think(String name) throws InterruptedException {
        Thread.sleep(500);
        System.out.println(name + " filozofuje");
    }
}
