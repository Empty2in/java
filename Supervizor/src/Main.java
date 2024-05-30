import AbstractProg.*;
import Supervisor.Supervisor;

void main() {
    Object myLock = new Object();
    System.out.println("Program start work.\n");
    new Thread(new Supervisor(new AbstractProg(myLock))).start();
}