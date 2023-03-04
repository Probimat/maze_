package sk.maze;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 * Solver Class, it contains a complete solution.
 * @author Maros Uhliar
 */
public class Solver {
    ArrayList<String> gamePlan;

    public Solver(ArrayList<String> gamePlan) {
        this.gamePlan = gamePlan;
    }
    
    // Finds all neighbors within the map around the cell.
    public ArrayList<PairOfNumbers> neighbours (PairOfNumbers pair) {
        ArrayList<PairOfNumbers> result = new ArrayList<>();
        if (pair.getX()-1 >= 0) result.add(new PairOfNumbers(pair.getX()-1, pair.getY()));
        if (pair.getX()+1 <= gamePlan.size()-1) result.add(new PairOfNumbers(pair.getX()+1, pair.getY()));
        if (pair.getY()-1 >= 0) result.add(new PairOfNumbers(pair.getX(), pair.getY()-1));
        if (pair.getY()+1 <= gamePlan.get(pair.getX()).length()-1) result.add(new PairOfNumbers(pair.getX(), pair.getY()+1));
        return result;
    }
    
    // Help function that searches for a pair with coordinates.
    public boolean containPairInListOfPairs (ArrayList<PairOfNumbers> allList, PairOfNumbers pair) {
        for (PairOfNumbers i : allList) {
            if (i.equals(pair)) return true;
        }
        return false;
    }
    
    // Find coordinates finish ('X'), if not exists return invalid coordinates.
    public PairOfNumbers findFinish() {
        for (int i = 0; i < gamePlan.size(); ++i) {
            for (int j = 0; j < gamePlan.get(i).length(); ++j) {
                if (gamePlan.get(i).charAt(j) == 'X') return new PairOfNumbers(i, j);
            }
        }
        return new PairOfNumbers(-1, -1);
    }
    
    // Step-by-step write of the path. 
    public void writeSolution(ArrayList<PairOfNumbers> path){
        for (int i=0; i< path.size()-1; i++) {
            if(path.get(i).getX() > path.get(i+1).getX()) System.out.print("u");
            if(path.get(i).getX() < path.get(i+1).getX()) System.out.print("d");
            if(path.get(i).getY() > path.get(i+1).getY()) System.out.print("l");
            if(path.get(i).getY() < path.get(i+1).getY()) System.out.print("r");
            if (i != path.size()-2) System.out.print(",");
        }
    }
    
    // Function create new File, and write original game plan with the shortest path between start and finish.
    public void writeSolutionToFile(ArrayList<PairOfNumbers> path) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("solution.txt", "UTF-8");
        for (int i = 0; i<gamePlan.size(); i++) {
            for (int j = 0; j< gamePlan.get(i).length(); j++) {
                if (containPairInListOfPairs(path, new PairOfNumbers(i,j)) && gamePlan.get(i).charAt(j) == '.') {
                    writer.print('o');
                } else {
                    writer.print(gamePlan.get(i).charAt(j));
                }
            }   
            writer.println();
        }
        writer.close();
    }
    
    // Collecting path into the ArrayList.
    public ArrayList<PairOfNumbers> loadingPath(PairOfNumbers start, Node node) {
        ArrayList<PairOfNumbers> path = new ArrayList<>();
        path.add(start);  // start position
        Node res = node.prev;
            while (res.prev != null) {
                path.add(res.getCoordinates());
                res = res.prev;
            }
        path.add(res.getCoordinates());  // finish position
        return path;
    }
    
    /** Basic BFS algorithm with queue (It starts from the finish, 
     *  the path does not need to be reversed later). It founds the
     *  shortest path, if exists. */
    public void solve() {
        ArrayList<PairOfNumbers> visited = new ArrayList<>();
        PairOfNumbers finish = findFinish();
        visited.add(finish);
        Queue <Node> fifo = new LinkedList<>();
        fifo.add(new Node(finish, null));
        
        while (!fifo.isEmpty()) {
            Node actualNode = fifo.remove();
            for (PairOfNumbers neighbour : neighbours(actualNode.getCoordinates())) {
                if (!containPairInListOfPairs(visited, neighbour)) {    
                    if (gamePlan.get(neighbour.getX()).charAt(neighbour.getY()) != '#') {
                        Node nextVisited = new Node(neighbour, actualNode);
                        if (gamePlan.get(neighbour.getX()).charAt(neighbour.getY()) == 'S') {
                            ArrayList<PairOfNumbers> path = loadingPath(neighbour, nextVisited);
                            writeSolution(path);
                            /*try {
                                writeSolutionToFile(path);
                            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                                Logger.getLogger(Solver.class.getName()).log(Level.SEVERE, null, ex);
                            }*/
                            return;
                        }
                        fifo.add(nextVisited);
                        visited.add(neighbour);
                    }
                }
            }
        }
        System.err.print("Not exists a Solution.");
    }
    
}
