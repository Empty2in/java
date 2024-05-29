package Supervisor;
import AbstractProg.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.lang.StringTemplate.STR;

public class Supervisor implements Runnable {

    private final Thread programThread;
    private final AbstractProgInterface program;
    private final String myLock;
    private static final Logger logger = Logger.getLogger(Supervisor.class.getName());

    public Supervisor(AbstractProgInterface program) {
        this.program = program;
        this.programThread = new Thread(program);
        this.myLock = program.getLock();
    }

    public void startProgram() {
        synchronized (myLock) {
            printMessage("supervisor restarts the program.");
            program.setState(State.RUNNING);
        }
    }

    public void stopProgram() {
        synchronized (myLock) {
            printMessage("supervisor stops the program immediately.");
            programThread.interrupt();
        }
    }

    private void printMessage(final String mess) {
        System.out.println(STR."\{program.getState()}: \{mess}");
    }

    @Override
    public void run() {

        System.out.println("Supervisor starts working!");

        programThread.start();
        startProgram();

        while (!programThread.isInterrupted()) {
            synchronized (myLock) {
                try {
                    myLock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    logger.log(Level.SEVERE, "Supervisor thread was interrupted", e);
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
