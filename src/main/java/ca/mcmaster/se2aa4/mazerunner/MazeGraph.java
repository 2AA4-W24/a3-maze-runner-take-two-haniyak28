package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class MazeGraph implements MazeType {
    private static final Logger logger = LogManager.getLogger();
    private final Map<GraphNode, List<GraphNode>> mazeGraph = new HashMap<>();
    // Class to represent a cell in the maze
    // Convert maze to graph represented as an adjacency list
    Map<GraphNode, List<GraphNode>> mazeToGraph(List<List<Boolean>> maze) {
        Map<GraphNode, List<GraphNode>> graph = new HashMap<>();
        int rows = maze.size();
        int cols = maze.get(0).size();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!maze.get(i).get(j)) { // If it's an open space
                    List<GraphNode> neighbors = new ArrayList<>();
                    // Check adjacent cells
                    if (i > 0 && !maze.get(i - 1).get(j)) {
                        neighbors.add(new GraphNode(i - 1, j, false));
                    }
                    if (i < rows - 1 && !maze.get(i + 1).get(j)) {
                        neighbors.add(new GraphNode(i + 1, j, false));
                    }
                    if (j > 0 && !maze.get(i).get(j - 1)) {
                        neighbors.add(new GraphNode(i, j - 1, false));
                    }
                    if (j < cols - 1 && !maze.get(i).get(j + 1)) {
                        neighbors.add(new GraphNode(i, j + 1, false));
                    }
                    mazeGraph.put(new GraphNode(i, j, false), neighbors);
                }
            }
        }
        return mazeGraph;
    }
    /*private static void connectAdjacentNodes(List<List<GraphNode>> graph) {
        int numRows = graph.size();
        int numCols = graph.get(0).size();

            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    GraphNode currentNode = graph.get(i).get(j);
                    // Connect currentNode to its neighboring nodes if they are open spaces
                    // Implement this based on your specific adjacency requirements
                }
            }
    }*/

    @Override
    public List<List<Boolean>> asList() {
        throw new UnsupportedOperationException("Cannot represent graph maze as list");
    }

    @Override
    public Map<GraphNode, List<GraphNode>> asGraph() {
        return mazeGraph;
    }
}

/*import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MazeGraph implements MazeType {
    private static final Logger logger = LogManager.getLogger();

    private final List<List<Boolean>> maze = new ArrayList<>();
    private final Graph graph = new Graph();

    public MazeGraph(String filePath) throws Exception {
        logger.debug("Reading the maze from file " + filePath);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int y = 0;
        while ((line = reader.readLine()) != null) {
            List<Boolean> newRow = new ArrayList<>();
            for (int x = 0; x < line.length(); x++) {
                char cell = line.charAt(x);
                // Add true for walls, false for open paths
                newRow.add(cell == '#');
                if (cell != '#') {
                    // Add node for each open cell
                    Position pos = new Position(x, y);
                    Node node = graph.addNode(pos);
                    // Connect to neighboring cells
                    connectNeighbors(pos, node);
                }
            }
            maze.add(newRow);
            y++;
        }
    }

    private void connectNeighbors(Position pos, Node node) {
        for (Direction dir : Direction.values()) {
            Position neighborPos = pos.move(dir);
            if (isValidPosition(neighborPos) && !isWall(neighborPos)) {
                Node neighborNode = graph.getNode(neighborPos);
                if (neighborNode != null) {
                    graph.addEdge(node, neighborNode);
                }
            }
        }
    }

    private boolean isValidPosition(Position pos) {
        int x = pos.x();
        int y = pos.y();
        return x >= 0 && x < maze.get(0).size() && y >= 0 && y < maze.size();
    }

    private boolean isWall(Position pos) {
        int x = pos.x();
        int y = pos.y();
        return maze.get(y).get(x);
    }

    // Implement other methods as needed for maze operations
}*/