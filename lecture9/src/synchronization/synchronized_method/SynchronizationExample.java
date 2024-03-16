package synchronization.synchronized_method;

public class SynchronizationExample {
    public static void main(String[] args) {
        Receiver target = new Receiver();
        Sender obj1    = new Sender(target, "obj1");
        Sender obj2    = new Sender(target, "obj2");
        Sender obj3    = new Sender(target, "obj3");

        // ожидать завершения потока исполнения
        try {
            obj1.thr.join();
            obj2.thr.join();
            obj3.thr.join();
        } catch (InterruptedException e) {
            System.out.println("прервано");
        }
    }
}
