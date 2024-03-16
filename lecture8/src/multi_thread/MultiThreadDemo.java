package multi_thread;

public class MultiThreadDemo {
    public static void main(String[] args) {
        new NewThread("Первый"); // запустить потоки на исполнение
        new NewThread("Второй");
        new NewThread("Третий");

        try {
            // ожидать завершения других потоков исполнения
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("Глaвный поток прерван.");
        }

        System.out.println("Глaвный поток завершен.");
    }
}
