import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import sk.maze.FindPathInputReaderFile;

/**
 * Simple tests for input file.
 * @author Maros Uhliar
 */
public class FindPathInputReaderFileTest {
    
    @Test
    public void checkReaderElementsFromFile() throws IOException{
        File tempFile = File.createTempFile("file-", ".tmp");
        tempFile.deleteOnExit();
        FileWriter fileWriter = new FileWriter(tempFile, true);
        try (BufferedWriter bw = new BufferedWriter(fileWriter)) {
            bw.write("Hele\nKarle\nBrno");
        }
        
        FindPathInputReaderFile obj = new FindPathInputReaderFile(tempFile);
        ArrayList<String> compare = new ArrayList<>();
        compare.add("Hele");
        compare.add("Karle");
        compare.add("Brno");
        assertEquals(obj.parentReader.getInputPlan(), compare);
        assertEquals(obj.parentReader.getInputPlan().get(0), "Hele");
        assertEquals(obj.parentReader.getInputPlan().get(1), "Karle");
        assertEquals(obj.parentReader.getInputPlan().get(2), "Brno");
    }
    
    @Test 
    public void checkCountOfElementsFromFile() throws IOException {
        File tempFile = File.createTempFile("file-", ".tmp");
        tempFile.deleteOnExit();
        FileWriter fileWriter = new FileWriter(tempFile, true);
        try (BufferedWriter bw = new BufferedWriter(fileWriter)) {
            bw.write("Hele\nKarle\nBrno");
        }
        FindPathInputReaderFile obj = new FindPathInputReaderFile(tempFile);
        assertEquals(3, obj.parentReader.getInputPlan().size());
    }
    
}
