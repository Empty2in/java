package Chess.FirstChessThreads;

import Chess.source.ChessParse;

public class Main {
    public static void main(String[] args) {
        ChessParse example = new ChessParse("source/Chess");

        Thread white = new Thread(new TakeStepsThread(example.white, "ThWhite"));
        Thread black = new Thread(new TakeStepsThread(example.black, "ThBlack"));

        white.start();
        black.start();

    }

}
