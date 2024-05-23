package Supervisor;

import AbstractProg.*;

public class Supervisor implements Runnable {

    private final Thread program;

    public Supervisor(AbstractProg program) {
        this.program = new Thread(program);
    }

    public void startProgram() {
        System.out.println(AbstractProg.state + ": supervisor restarts the program.");
        AbstractProg.state = State.RUNNING;
    }

    public void stopProgram() {
        System.out.println(AbstractProg.state + ": supervisor stops the program immediately.");
        program.interrupt();
    }

    @Override
    public void run() {

        System.out.println("Supervisor starts working!");

        program.start();
        startProgram();

        while (!program.isInterrupted()) {
            synchronized (AbstractProg.myLock) {
                try {
                    AbstractProg.myLock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                State currentState = AbstractProg.state;
                switch (currentState) {
                    case STOPPING, UNKNOWN -> startProgram();
                    case FATAL_ERROR -> stopProgram();
                    default -> System.out.println(currentState + ": supervisor monitors the program.");
                }

            }
        }
    }
}
