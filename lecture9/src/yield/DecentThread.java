package yield;

// Вежливый поток, уступающий процессорное время
public class DecentThread extends Thread {

    public DecentThread() {
        this.start();
    }

    public void run() {

        System.out.println(Thread.currentThread().getName() + " уступает свое место другим");
        Thread.yield(); // Указание, что текущий поток может уступить другим процессорное время
        System.out.println(Thread.currentThread().getName() + " has finished executing.");
    }
}
