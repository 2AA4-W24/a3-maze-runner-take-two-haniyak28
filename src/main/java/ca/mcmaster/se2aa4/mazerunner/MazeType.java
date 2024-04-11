package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public interface MazeType {
    List<List<Boolean>> asList();

    GraphNode[][] asGraph();
}
