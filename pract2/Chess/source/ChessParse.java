package Chess.source;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChessParse {

    public List<String> white = new ArrayList<>();
    public List<String> black = new ArrayList<>();

    public ChessParse(String path) {
        try (Scanner scanner = new Scanner(new FileReader(path))) {
            while (scanner.hasNextLine()) {
                String[] whiteAndBlack = scanner.nextLine().split(" - ");
                white.add(whiteAndBlack[0]);
                black.add(whiteAndBlack[1]);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void printSteps() {
        System.out.print("White steps: ");
        white.forEach(System.out::println);

        System.out.print("Black steps: ");
        black.forEach(System.out::println);
    }

}
