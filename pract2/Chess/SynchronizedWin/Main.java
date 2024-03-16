package Chess.SynchronizedWin;

import Chess.source.ChessParse;

public class Main {
    public static void main(String[] args) {

        ChessParse example = new ChessParse("pract2/Chess/source/Chess");

        Thread white = new Thread(new ChessThread(example.white, "ThWhite"));
        Thread black = new Thread(new ChessThread(example.black, "ThBlack"));

        white.start();
        black.start();
    }
}
