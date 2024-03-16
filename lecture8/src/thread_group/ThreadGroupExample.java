package thread_group;

import multi_thread.NewThread;

public class ThreadGroupExample {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group = new ThreadGroup("GroupA");

        new Task(group, "A").start();
        new Task(group, "B").start();
        new Task(group, "C").start();
        new Task(group, "D").start();

        System.out.println("Активных потоков в группе: " + group.activeCount());

        Thread.sleep(10000);

        group.interrupt();

    }
}
