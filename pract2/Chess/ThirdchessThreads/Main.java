package Chess.ThirdchessThreads;

import Chess.source.ChessParse;

public class Main {
    public static void main(String[] args) {
        ChessParse example = new ChessParse("pract2/Chess/source/Chess");

        ChessStepsArray whiteArray = new ChessStepsArray(example.white);
        ChessStepsArray blackArray = new ChessStepsArray(example.black);


        Thread white = new Thread(new ChessThread(whiteArray, "ThWhite"));
        Thread black = new Thread(new ChessThread(blackArray, "ThBlack"));

        white.start();
        black.start();
    }
}
