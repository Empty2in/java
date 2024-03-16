package synchronized_block;

public class SynchronizationExample {
    public static void main(String[] args) {
        Receiver target = Receiver.getInstance();
        Receiver target2 = Receiver.getInstance();
        Sender obj1    = new Sender(target, "obj1");
        Sender obj2    = new Sender(target, "obj2");
        Sender obj3    = new Sender(target2, "obj3");
        Sender obj4    = new Sender(target2, "obj4");

        // ожидать завершения потока исполнения
        try {
            obj1.thr.join();
            obj2.thr.join();
            obj3.thr.join();
            obj4.thr.join();
        } catch (InterruptedException e) {
            System.out.println("прервано");
        }
    }
}
