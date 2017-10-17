package pl.sdacademy.maze;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RENT on 2017-07-25.
 */

/* before running set console font to Consolas to properly see the result */
public class Maze {

    private char[][] fields;

    public Maze(String fileName) {
        List<String> mazeLines = new ArrayList<>();
        try {
            mazeLines = Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        fields = new char[mazeLines.size()][];
        for(int i = 0; i < mazeLines.size(); i++) {
            fields[i] = new char [mazeLines.get(i).length()];
            for(int j = 0; j < mazeLines.get(i).length(); j++) {
                fields[i][j] = mazeLines.get(i).charAt(j);
            }
        }
    }

    public char[][] getFields() {
        return fields;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(char[] row : fields) {
            for(char field : row) {
                result.append(field);
            }
            result.append("\n");
        }
        return result.toString();
    }

    private Point getPoint(char c) {
        for(int i = 0; i < fields.length; i++) {
            for(int j = 0; j < fields[i].length; j++) {
                if(fields[i][j] == c) {
                    return new Point(j, i);
                }
            }
        }
        return null;
    }

    public Point getStartPoint() {
        Point startPoint = getPoint('S');
        if(startPoint == null) {
            throw new RuntimeException("Brak punktu startowego w labiryncie.");
        } else {
            return startPoint;
        }
    }

    public Point getEndPoint() {
        Point endPoint = getPoint('E');
        if(endPoint == null) {
            throw new RuntimeException("Brak punktu końcowego w labiryncie.");
        } else {
            return endPoint;
        }
    }

    public Character getFieldAt(Point point) {
        if (point.getX() >= 0 && point.getY() >= 0 && point.getY() < fields.length && point.getX() < fields[point.getY()].length) {
            return fields[point.getY()][point.getX()];
        } else {
            return null;
        }
    }
}
