package Supervisor;
import AbstractProg.*;
import static java.lang.StringTemplate.STR;


public class Supervisor implements Runnable {

    private final Thread programThread;
    private final AbstractClassProg program;

    public Supervisor(AbstractClassProg program) {
        this.program = program;
        this.programThread = new Thread(program);
    }

    public void startProgram() {
        synchronized (AbstractProg.myLock) {
            printMessage("supervisor restarts the program.");
            program.setState(State.RUNNING);
        }
    }

    public void stopProgram() {
        synchronized (AbstractProg.myLock) {
            printMessage("supervisor stops the program immediately.");
            programThread.interrupt();
        }
    }

    private void printMessage(final String mess) {
        System.out.println(STR."\{program.getState()}: \{mess} \{program.getName()}");
    }

    @Override
    public void run() {

        System.out.println("Supervisor starts working!");

        programThread.start();
        startProgram();

        while (!programThread.isInterrupted()) {
            synchronized (AbstractProg.myLock) {
                try {
                    AbstractProg.myLock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                State currentState = program.getState();
                switch (currentState) {
                    case STOPPING, UNKNOWN -> startProgram();
                    case FATAL_ERROR -> stopProgram();
                    default -> printMessage("supervisor monitors the program");
                }
            }
        }
    }
}
