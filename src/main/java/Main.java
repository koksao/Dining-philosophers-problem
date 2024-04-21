import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        final Object monitor = new Object();


        boolean[] forks = new boolean[5];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = true;
        }
//
//        Thread thread1 = new Thread(new BothForksPhilosopher("Philosopher1", forks, 0, 4, monitor));
//        Thread thread2 = new Thread(new BothForksPhilosopher(("Philosopher2", forks, 0, 1, monitor));
//        Thread thread3 = new Thread(new BothForksPhilosopher("Philosopher3", forks, 1, 2, monitor));
//        Thread thread4 = new Thread(new BothForksPhilosopher("Philosopher4", forks, 2, 3, monitor));
//        Thread thread5 = new Thread(new BothForksPhilosopher("Philosopher5", forks, 3, 4, monitor));
        Thread thread1 = new Thread(new OneByOnePhilosopher("Philosopher1", 1, 0,4, forks));
        Thread thread2 = new Thread(new OneByOnePhilosopher("Philosopher2", 2, 1,0, forks));
        Thread thread3 = new Thread(new OneByOnePhilosopher("Philosopher3", 3, 2,1, forks));
        Thread thread4 = new Thread(new OneByOnePhilosopher("Philosopher4", 4, 3,2, forks));
        Thread thread5 = new Thread(new OneByOnePhilosopher("Philosopher5", 5, 4,3, forks));

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();


    }


}

