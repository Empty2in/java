package starvation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Worker {

    public synchronized void work() {
        String name = Thread.currentThread().getName();
        String fileName = name + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Thread " + name + " wrote this message");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            System.out.println(name + " is working");
            try {
                wait(10000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }


    }
}