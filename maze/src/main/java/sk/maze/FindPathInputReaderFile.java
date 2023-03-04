package sk.maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class for read from File.
 * @author Maros Uhliar
 */
public class FindPathInputReaderFile extends AbstractFindPathInputReader {
    public AbstractFindPathInputReader parentReader = new AbstractFindPathInputReader() {};

    public FindPathInputReaderFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        parentReader.read(scanner);
    }    
    
}
