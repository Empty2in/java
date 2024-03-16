package wait_set;

public class WaitSetExample {
    public static void main(String[] args) {
        Network network = new Network();
        Thread sender = new Thread(new Sender(network));
        Thread receiver = new Thread(new Receiver(network));

        sender.start();
        receiver.start();
    }
}
