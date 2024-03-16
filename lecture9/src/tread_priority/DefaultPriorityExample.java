package tread_priority;

public class DefaultPriorityExample {
    public static void main(String s[]) {

        // Подготовка потоков
        Thread[] t = new Thread[3];
        for (int i = 0; i < t.length; i++) {
            t[i] = new Thread(new ThreadTest(),
                    "Thread " + i);
        }

        // Запуск потоков
        for (Thread thread : t) {
            thread.start();
            System.out.println(thread.getName() +
                    " started");
        }
    }
}
