package thread_group;

public class Task extends Thread {
    public Task(ThreadGroup threadGroup, String name) {
        super(threadGroup, name);
    }

    public void run() {
        boolean running = true;

        while (running) {
            try {
                System.out.println(getName() + " is running");
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                running = false;
                System.out.println(getName() + " is interrupted and then terminates");
            }
        }
    }
}