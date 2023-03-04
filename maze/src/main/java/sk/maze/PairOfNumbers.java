package sk.maze;

/**
 * Help class, designed for two coordinates.
 * @author Maros Uhliar
 */
public class PairOfNumbers {
    public int x,y;

    public PairOfNumbers(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    @Override
   public boolean equals(Object other) {
      if (!(other instanceof PairOfNumbers)) {
        return false;
      }
      
      PairOfNumbers p = (PairOfNumbers) other;
      try {
        return this.x== p.x && this.y == p.y;
      } catch (Exception e) {
        return false;
      }
   }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + this.x;
        hash = 13 * hash + this.y;
        return hash;
    }
    
}
