package Supervisor;

import AbstractProg.*;

/**
 * Supervisor class that controls the operation of an abstract program
 */
public class Supervisor implements Runnable {

    private final Thread programThread;
    private final AbstractProg program;
    private final Object myLock; //mutex

    public Supervisor(AbstractProg program) {
        this.program = program;
        this.programThread = new Thread(program);
        this.myLock = program.getLock();
    }

    public void startProgram() {
        //capturing the monitor by locking a specific program to start it
        printMessage("supervisor restarts the program.");
        program.setState(ProgState.RUNNING);
    }

    public void stopProgram() {
        //capturing the monitor by locking a specific program to stop it
        printMessage("supervisor stops the program immediately.");
        programThread.interrupt();
    }


    @Override
    public void run() {

        System.out.println("Supervisor starts working!");

        //starting the program thread and the program itself
        programThread.start();
        startProgram();

        while (!programThread.isInterrupted()) {

            synchronized (myLock) {
                //expect a change in the program state
                try {
                    myLock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Supervisor thread was interrupted.");
                }

                //obtaining program state and performing appropriate actions
                ProgState currentState = program.getState();
                switch (currentState) {
                    case STOPPING, UNKNOWN -> startProgram();
                    case FATAL_ERROR -> stopProgram();
                    case RUNNING -> printMessage("supervisor monitors the program.");
                    default -> printMessage("supervisor doesn't now this state.");
                }
            }
        }
    }

    private void printMessage(final String mess) {
        System.out.println(STR."\{program.getState()}: \{mess}");
    }
}
