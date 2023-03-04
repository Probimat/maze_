package sk.maze;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that reads a line.
 * @author Maros Uhliar
 */
public abstract class AbstractFindPathInputReader {
    public static ArrayList<String> inputPlan;

    public AbstractFindPathInputReader() {
        inputPlan = new ArrayList<>();
    }

    public ArrayList<String> getInputPlan() {
        return inputPlan;
    }
    
    public void read(Scanner scanner) {                
        try {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                inputPlan.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
