package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;
import java.util.Map;

public interface MazeType {
    List<List<Boolean>> asList();

    Map<GraphNode, List<GraphNode>> asGraph();
}
