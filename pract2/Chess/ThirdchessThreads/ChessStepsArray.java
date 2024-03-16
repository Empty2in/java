package Chess.ThirdchessThreads;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ChessStepsArray {
    private ReentrantLock locker = new ReentrantLock(); // создаем объект ReentrantLock
    private long amount = 0L;

    List<String> steps;
    public ChessStepsArray(List<String> steps) {
        this.steps = steps;
    }

    public void print(String threadName, String step) {
        locker.lock(); // устанавливаем блокировку

        try {
            System.out.println(threadName + " " + step);
        } finally {
            locker.unlock(); // непременно снять блокировку, даже если генерируется исключение
        }
    }

    public long getAmount() {
        return amount;
    }
}
