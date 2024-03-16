package Chess.LockWin;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ChessThread implements Runnable {

    ReentrantLock lock = new ReentrantLock();  // блокировка
    Condition cond = lock.newCondition();  // условие блокировки

    private final List<String> steps;
    private final String name;

    private static int numOfThreads;
    private static int currentThreadId;
    private final int threadId;

    public ChessThread(List<String> steps, String name) {
        threadId = numOfThreads++;
        this.steps = steps;
        this.name = name;
    }

    @Override
    public void run() {
        for (String step: steps) {
            lock.lock();
            try {
                while (currentThreadId != threadId) {
                    cond.await();
                }
                System.out.println(name + ": " + step);
                currentThreadId = threadId == numOfThreads - 1 ? 0 : threadId + 1;
                cond.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}