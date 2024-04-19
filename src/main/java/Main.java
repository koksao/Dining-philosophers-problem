import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        final Object monitor = new Object();

        boolean[] forks = new boolean[5];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = true;
        }

        Thread thread1 = new Thread(new Philosopher("Philosopher1", forks, 0, 4, monitor));
        Thread thread2 = new Thread(new Philosopher("Philosopher2", forks, 0, 1, monitor));
        Thread thread3 = new Thread(new Philosopher("Philosopher3", forks, 1, 2, monitor));
        Thread thread4 = new Thread(new Philosopher("Philosopher4", forks, 2, 3, monitor));
        Thread thread5 = new Thread(new Philosopher("Philosopher5", forks, 3, 4, monitor));

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();


    }


}

