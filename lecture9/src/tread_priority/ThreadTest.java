package tread_priority;

public class ThreadTest implements Runnable {
    public void run() {
        double calc;
        for (int i = 0; i < 500000; i++) {
            calc = Math.sin(i * i);
            if (i % 10000 == 0) {
                System.out.println(getName() +
                        " counts " + i / 10000);
            }
        }
    }

    public String getName() {
        return Thread.currentThread().getName();
    }
}