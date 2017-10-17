package pl.sdacademy.maze;


/**
 * Created by RENT on 2017-07-25.
 */
public class State {

    private Point point;
    private Point origin;
    private boolean visited;
    private int scoreFnValue;

    public State(Point point, Point origin, int scoreFnValue) {
        this.point = point;
        this.origin = origin;
        this.scoreFnValue = scoreFnValue;
        this.visited = false;
    }

    public Point getPoint() {
        return point;
    }

    public Point getOrigin() {
        return origin;
    }

    public boolean isVisited() {
        return visited;
    }

    public int getScoreFnValue() {
        return scoreFnValue;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
