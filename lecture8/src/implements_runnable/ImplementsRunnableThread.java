package implements_runnable;

// Создать второй поток исполнения через реализацию интерфейса Runnable
public class ImplementsRunnableThread implements Runnable {
    private Thread t;

    public ImplementsRunnableThread() {

        // Cоздать новый, второй поток исполнения !!!
        t = new Thread(this, "Демонстрационный поток");

        System.out.println("Дoчepний поток создан: " + t);
        t.start(); // запустить поток исполнения
    }

    // Точка входа во второй поток исполнения
    public void run() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Дoчepний поток: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Дoчepний поток прерван.");
        }
        System.out.println("Дoчepний поток завершен.");
    }
}



