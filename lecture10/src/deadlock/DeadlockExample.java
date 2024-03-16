package deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockExample {

    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {
        DeadlockExample deadlock = new DeadlockExample();
        new Thread(deadlock::operation1, "T1").start();
        new Thread(deadlock::operation2, "T2").start();
    }

    public void operation1() {
        try {
            lock1.lock();
            System.out.println("lock1 acquired, waiting to acquire lock2.");
            Thread.sleep(50);

            lock2.lock();
            System.out.println("lock2 acquired");

            System.out.println("executing first operation.");

            lock2.unlock();
            lock1.unlock();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
            throw new RuntimeException(e);
        }
    }

    public void operation2() {
        try {
            lock2.lock();
            System.out.println("lock2 acquired, waiting to acquire lock1.");
            Thread.sleep(50);

            lock1.lock();
            System.out.println("lock1 acquired");

            System.out.println("executing second operation.");

            lock1.unlock();
            lock2.unlock();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
            throw new RuntimeException(e);
        }
    }

    // helper methods

}