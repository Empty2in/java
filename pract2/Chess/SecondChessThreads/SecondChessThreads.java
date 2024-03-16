package Chess.SecondChessThreads;

import Chess.source.ChessParse;

import java.util.List;

public class SecondChessThreads {
    public static void main(String[] args) {

        ChessParse example = new ChessParse("pract2/Chess/source/Chess");

        Thread white = new Thread(new WhiteChessThread(example.white, "ThWhite"));
        Thread black = new Thread(new BlackChessThread(example.black, "ThBlack"));

        white.start();
        black.start();
    }
}

class WhiteChessThread implements Runnable {
    final List<String> steps;
    String name;

    public WhiteChessThread(List<String> newSteps, String newName) {
        steps = newSteps;
        name = newName;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < steps.size()) {
            System.out.println(name + " " + steps.get(i));
            i++;
            //sleep(2000);
                  /*
                    Перед msg.notify(); нужно захватить msg в монопольное использование,
                    чтобы убедиться, что никто кроме этого потока не имеет доступа к объекту.
                     */
            synchronized (steps) {
                steps.notify();
            }
        }
    }

    private void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class BlackChessThread implements Runnable {

    final List<String> steps;
    String name;

    public BlackChessThread(List<String> newSteps, String newName) {
        steps = newSteps;
        name = newName;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < steps.size()) {
            synchronized (steps) {
                try {
                    System.out.println(name + " " + steps.get(i));

                    steps.wait();
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
