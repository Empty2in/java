import AbstractProg.AbstractProg;
import Supervisor.Supervisor;

void main() {
    System.out.println("Program start work.\n");
    new Thread(new Supervisor(new AbstractProg("biba"))).start();
    //new Thread(new Supervisor(new AbstractProg("boba"))).start();
    //new Thread(new Supervisor(new AbstractProg("zeliboba"))).start();
}