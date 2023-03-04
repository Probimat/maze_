package sk.maze;

/**
 * Help Class, designed for information of Node coordinations, and Node which discovered its.
 * @author Maros Uhliar
 */
public class Node {
    public PairOfNumbers coordinates;
    public Node prev;

    public Node(PairOfNumbers coordinates, Node prev) {
        this.coordinates = coordinates;
        this.prev = prev;
    }

    public PairOfNumbers getCoordinates() {
        return coordinates;
    }

    public Node getPrev() {
        return prev;
    }
    
}
