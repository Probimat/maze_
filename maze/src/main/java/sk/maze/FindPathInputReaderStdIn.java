package sk.maze;

import java.util.Scanner;

/**
 * Class for read from console.
 * @author Maros Uhliar
 */
public class FindPathInputReaderStdIn extends AbstractFindPathInputReader {
    AbstractFindPathInputReader parentReader = new AbstractFindPathInputReader() {};

    public FindPathInputReaderStdIn() {
        Scanner scanner = new Scanner(System.in);
        parentReader.read(scanner);
    }    
    
}
