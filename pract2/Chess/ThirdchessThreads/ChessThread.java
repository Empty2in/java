package Chess.ThirdchessThreads;

import java.util.List;

public class ChessThread implements Runnable {

    ChessStepsArray steps;
    String name;
    public ChessThread(ChessStepsArray newSteps, String newName) {
        steps = newSteps;
        name = newName;
    }

    @Override
    public void run() {
        for (String step : steps.steps) {
            steps.print(name, step);
        }
    }


}
