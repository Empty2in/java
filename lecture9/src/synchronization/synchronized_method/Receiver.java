package synchronization.synchronized_method;

public class Receiver {
    // Этот метод принимает параметр msg типа String и пытается вывести символьную строку в квадратных скобках
    synchronized void receiver(String msg) {
        System.out.print("[" + msg);
        // после того, как метод call() выведет символьную строку,
        // он вызывает метод Thread.sleep(1000) и приостанавливает текущий поток исполнения на 1 секунду
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("прервано");
        }
        System.out.println("]");
    }
}
