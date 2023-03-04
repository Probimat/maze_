import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import sk.maze.PairOfNumbers;
import sk.maze.Solver;

/**
 * Simple tests for class Solver.
 * @author Maros Uhliar
 */
public class SolverTest {
    
    @Test
    public void notExistsSolution() {
        ArrayList<String> a = new ArrayList<>();
        a.add(".S....");
        a.add("######");
        a.add("....X.");
        Solver n = new Solver(a);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outContent));        
        n.solve();
        assertEquals("Not exists a Solution.", outContent.toString());
    }
    
    @Test
    public void simpleDownSolution(){
        ArrayList<String> a = new ArrayList<>();
        a.add("...S...");
        a.add(".......");
        a.add(".......");
        a.add("...X...");
        Solver s =  new Solver(a);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));        
        s.solve();
        assertEquals("d,d,d", outContent.toString());
    }
    
    @Test
    public void notContainsAndContainsPair() {
        ArrayList<PairOfNumbers> allList = new ArrayList<>();
        allList.add(new PairOfNumbers(1,2));
        allList.add(new PairOfNumbers(7,8));
        allList.add(new PairOfNumbers(4,6));
        Solver s = new Solver(new ArrayList<String>());
        assertEquals(s.containPairInListOfPairs(allList, new PairOfNumbers(1,3)), false);
        assertEquals(s.containPairInListOfPairs(allList, new PairOfNumbers(7,8)), true);
    }
    
    @Test
    public void findFinish(){
        ArrayList<String> a = new ArrayList<>();
        a.add("....S...");
        a.add("........");
        a.add("........");
        a.add("....X...");
        Solver s =  new Solver(a);
        assertEquals(s.findFinish(), new PairOfNumbers(3,4));
    }
    
    @Test
    public void notFindFinish(){
        ArrayList<String> a = new ArrayList<>();
        a.add("....S...");
        a.add("........");
        a.add("........");
        Solver s =  new Solver(a);
        assertEquals(s.findFinish(), new PairOfNumbers(-1,-1));
    }
    
    /** Generating Plan like (start and finish are in opposite corner):
     *   S....
     *   .....
     *   .....
     *   .....
     *   ....X
     */
    @Test
    public void prformanceTestEmptyPlan() {
        for (int i = 10; i < 100; i++) {
            ArrayList<String> a = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (j == 0) {
                    String line = "S";
                    for (int k = 1; k < i; ++k) {
                        line += ".";
                    }
                    a.add(line);
                } else if (j == i-1) {
                    String line = "";
                    for (int k = 1; k < i; ++k) {
                        line += ".";
                    }
                    line += "X";
                    a.add(line);
                } else {
                    String line = "";
                    for (int k = 0; k < i; ++k) {
                        line += ".";
                    }
                    a.add(line);
                }
            }
            Solver s =  new Solver(a);
            s.solve();
        }
    }
    
}
