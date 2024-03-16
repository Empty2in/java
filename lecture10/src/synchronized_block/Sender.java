package synchronized_block;

public class Sender implements Runnable {
    private final String msg;
    private final Receiver target;
    public Thread thr;

    // В этом конструкторе создается также новый поток исполнения,
    // в котором вызывается метод run() для данного объекта.
    public Sender(Receiver trg, String s) {
        target = trg;
        msg = s;
        thr = new Thread(this);
        thr.start();
    }

    public void run() {
        synchronized (target) { // синхронизированный блок
            target.receive(msg);
        }
    }
}