package pl.sdacademy.maze;

import java.util.*;

/**
 * Created by RENT on 2017-07-25.
 */
public class MazeSolver {

    private Maze maze;
    private Point endPoint;
    private Map<Point, State> stateByPoint;

    public MazeSolver(Maze maze) {
        this.maze = maze;
        this.endPoint = maze.getEndPoint();
        this.stateByPoint = new HashMap<>();
    }

    public List<Point> solve() {
        Point startPoint = maze.getStartPoint();
        State startPointState = new State(startPoint, null, getScoreFnValue(startPoint));
        stateByPoint.put(startPoint, startPointState);
        State currentState = getBestUnvisitedState();

        while(!currentState.getPoint().equals(endPoint)) {
            takeALookAround(currentState.getPoint());
            currentState.setVisited(true);
            currentState = getBestUnvisitedState();
        }
        return null;
    }

    private List<Point> getRoute() {
        List<Point> route = new ArrayList<>();
        Point currentPoint = endPoint;
        route.add(currentPoint);
        while(stateByPoint.get(currentPoint).getOrigin() != null) {
            currentPoint = stateByPoint.get(currentPoint).getOrigin();
            route.add(currentPoint);
        }
        Collections.reverse(route);
        return  route;
    }

    private char[][] printSolution() {
        List<Point> routeList = getRoute();
        char[][] solvedMaze = maze.getFields();
        for(int i = 1; i < routeList.size()-1; i++) {
            int x = routeList.get(i).getX();
            int y = routeList.get(i).getY();
            solvedMaze[y][x] = '*';
        }
        return solvedMaze;
    }

    private char[][] printSolution2() {
        List<Point> routeList = getRoute();
        char[][] solvedMaze = maze.getFields();
        for(int i = 1; i < routeList.size()-1; i++) {
            int prevX = stateByPoint.get(routeList.get(i)).getOrigin().getX();
            int prevY = stateByPoint.get(routeList.get(i)).getOrigin().getY();
            int x = routeList.get(i).getX();
            int y = routeList.get(i).getY();
            if(prevX+1 == x) {
                solvedMaze[y][x] = '→';
            } else if(prevX-1 == x) {
                solvedMaze[y][x] = '←';
            } else if(prevY+1 == y) {
                solvedMaze[y][x] = '↓';
            } else if(prevY-1 == y) {
                solvedMaze[y][x] = '↑';
            }
        }
        return solvedMaze;
    }

    public String printSolvedMaze() {
        char[][] solvedMaze = printSolution2();
        StringBuilder result = new StringBuilder();
        for(char[] row : solvedMaze) {
            for(char field : row) {
                result.append(field);
            }
            result.append("\n");
        }
        result.append("Liczba kroków potrzebna do rozwiązania labiryntu to: " + getRoute().size()+1);
        return result.toString();
    }

    private void takeALookAround(Point point) {
        Point leftPoint = new Point(point.getX()-1, point.getY());
        Point rightPoint = new Point(point.getX()+1, point.getY());
        Point upperPoint = new Point(point.getX(), point.getY()-1);
        Point lowerPoint = new Point(point.getX(), point.getY()+1);
        List<Point> surroundings = Arrays.asList(leftPoint, rightPoint, upperPoint, lowerPoint);
        for (Point surrounding : surroundings) {
            if(maze.getFieldAt(surrounding) != null && (maze.getFieldAt(surrounding) == ' ' ||  maze.getFieldAt(surrounding) == 'E')  && !stateByPoint.containsKey(surrounding)) {
                stateByPoint.put(surrounding, new State(surrounding, point, getScoreFnValue(surrounding)));
            }
        }
    }

    private State getBestUnvisitedState() {
     return stateByPoint.values().stream().filter(s -> !s.isVisited())
                .min((s1, s2) -> s1.getScoreFnValue() - s2.getScoreFnValue())
                .orElseThrow(() -> new RuntimeException("Brak punktu potencjalnego"));


    }

    private int getScoreFnValue(Point point) {
        return Math.abs((endPoint.getX() - point.getX())) + Math.abs((endPoint.getY() - point.getY()));
    }
}
