import java.util.Random;
import java.util.concurrent.*;

public class Philosopher implements Runnable {
    Random random = new Random();
    int randomSleep = random.nextInt(2000 - 1000 + 1) + 1000;
    boolean[] forks;
    String name;
    int fork1Index;
    int fork2Index;
    final Object monitor;

    public Philosopher(String name, boolean[] forks, int fork1Index, int fork2Index, Object monitor) {
        this.name = name;
        this.forks = forks;
        this.fork1Index = fork1Index;
        this.fork2Index = fork2Index;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        try {
            while (true) {
                think(name);
                synchronized (monitor) {
                    askForForks(fork1Index, fork2Index);
                }
                eating();
                synchronized (monitor) {
                    releaseForks(fork1Index, fork2Index);
                }
            }
        } catch (InterruptedException e) {
        }

    }

    public void askForForks(int fork1Index, int fork2Index) throws InterruptedException {
        while (!forks[fork1Index] || !forks[fork2Index]) {
            System.out.println(name + " czeka na widelce");
            monitor.wait();
        }
        forks[fork1Index] = false;
        forks[fork2Index] = false;
    }

    public void think(String name) throws InterruptedException {
        Thread.sleep(500);
        System.out.println(name + " filozofuje");
    }

    public void releaseForks(int fork1Index, int fork2Index) {
        System.out.println(name + " oddaje widelce");
        forks[fork1Index] = true;
        forks[fork2Index] = true;
        monitor.notifyAll();

    }

    public void eating() throws InterruptedException {
        System.out.println(name + " je spaget");
        Thread.sleep(randomSleep);
        System.out.println(name + " skończył jesc");
    }


}

