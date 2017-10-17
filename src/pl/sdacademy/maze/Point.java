package pl.sdacademy.maze;

/**
 * Created by RENT on 2017-07-26.
 */
public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object object) {
       if(object instanceof Point) {
           Point point = (Point) object;
           return point.getX() == this.getX() && point.getY() == this.getY();
       } else {
           return false;
       }

    }

    @Override
    public String toString() {
        return "(" + getX() + ", " + getY() + ")";
    }


    @Override
    public int hashCode() {
        return x * 1000000 + y;
    }
}
