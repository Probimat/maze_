package sk.maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * Primary class, detect only one input parameter.
 * @author Maros Uhliar
 */
public class Maze {
    
    public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
        if (args.length == 1) {
            File file = new File(args[0]);
                FindPathInputReaderFile obj1 = new FindPathInputReaderFile(file);
                Solver s = new Solver(obj1.parentReader.getInputPlan());
                s.solve();
        } else {
            FindPathInputReaderStdIn obj2 = new FindPathInputReaderStdIn();
            Solver s = new Solver(obj2.parentReader.getInputPlan());
            s.solve();
        }
    }
}
