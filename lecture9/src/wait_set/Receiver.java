package wait_set;

import java.util.concurrent.ThreadLocalRandom;

public class Receiver implements Runnable {
    private Network network;

    // standard constructors
    public Receiver(Network network) {
        this.network = network;
    }

    public void run() {
        for(String receivedMessage = network.receive();
            !"End".equals(receivedMessage);
            receivedMessage = network.receive()) {

            System.out.println(receivedMessage);

            //Thread.sleep() to mimic heavy server-side processing
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread Interrupted");
            }
        }
    }
}
