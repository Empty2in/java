package daemon_thread;

public class DaemonThreadExample {
    public static void main(String[] args) throws InterruptedException {

        // Runnable – функциональный интерфейс, можно использовать лямбда выражение

        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().isDaemon() ? "Поток демон" : "Поток не демон");
            for (int i = 5; i > 0; i--) {
                System.out.println("Счётчик внутри потока: \t" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread.setDaemon(true);

        thread.start();


        Thread.sleep(3000);

    }
}
