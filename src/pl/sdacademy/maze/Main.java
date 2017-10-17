package pl.sdacademy.maze;

/**
 * Created by RENT on 2017-07-25.
 */
public class Main {
    public static void main(String[] args) {
        Maze maze = new Maze("maze.txt");
        MazeSolver mazeSolver = new MazeSolver(maze);
        mazeSolver.solve();
        System.out.println(mazeSolver.printSolvedMaze());
    }
}
