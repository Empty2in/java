package implements_runnable;

class ImplementsRunnableThreadExample {
    public static void main(String[] args) {
        new ImplementsRunnableThread(); // создать новый поток

        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Глaвный поток: \t" + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Глaвный поток прерван.");
        }
        System.out.println("Глaвный поток завершен.");
    }
}