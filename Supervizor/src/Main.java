import AbstractProg.AbstractProg;
import Supervisor.Supervisor;

public class Main {
    public static void main(String[] args) {
        System.out.println("Program start work.\n");
        new Thread(new Supervisor(new AbstractProg())).start();
    }
}