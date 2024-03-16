package multi_thread;

public class ThreadJoinDemo {
    public static void main(String[] args) {
        NewThread ob1 = new NewThread("Один");
        NewThread ob2 = new NewThread("Два");
        NewThread ob3 = new NewThread("Три");
        System.out.println("Пoтoк Один запущен: " + ob1.t.isAlive());
        System.out.println("Пoтoк Два запущен:  " + ob2.t.isAlive());
        System.out.println("Пoтoк Три запущен:  " + ob3.t.isAlive());

        // ожидать завершения потоков исполнения
        try {
            System.out.println("Oжидaниe завершения потоков.");
            // Метод join() приостанавливает выполнение текущего потока до тех пор, пока не завершится другой поток.
            // Главный поток продолжит работу только после того, как ob1,2,3 завершат работу
            ob1.t.join();
            ob2.t.join();
            ob3.t.join();

        } catch (InterruptedException e) {
            System.out.println("Глaвный поток прерван");
        }
        System.out.println("Пoтoк Один запущен: " + ob1.t.isAlive());
        System.out.println("Пoтoк Два запущен:  " + ob2.t.isAlive());
        System.out.println("Пoтoк Три запущен:  " + ob3.t.isAlive());
        System.out.println("Глaвный поток завершен.");
    }
}
