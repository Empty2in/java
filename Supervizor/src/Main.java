import AbstractProg.AbstractProg;
import Supervisor.Supervisor;

void main() {
    String myLock = "";
    System.out.println("Program start work.\n");
    new Thread(new Supervisor(new AbstractProg(myLock))).start();
}