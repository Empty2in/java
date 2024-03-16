package extends_thread;

class ExtendsThreadDemo {
    public static void main(String[] args) {
        ExtendsThread extendsThread = new ExtendsThread();// создать новый поток исполнения
        extendsThread.start();

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