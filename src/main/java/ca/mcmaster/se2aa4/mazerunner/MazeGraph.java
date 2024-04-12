package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class MazeGraph {
    private static final Logger logger = LogManager.getLogger();
    private final Map<GraphNode, List<GraphNode>> mazeGraph = new HashMap<>();
    private final GraphNode StartNode;
    private final GraphNode EndNode;



    // Class to represent a cell in the maze
    // Convert maze to graph represented as an adjacency list
    public MazeGraph(Maze maze) throws Exception{
        int rows = maze.getSizeY();
        int cols = maze.getSizeX();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Position currentPos = new Position(i, j);
                if (!maze.isWall(currentPos)) { // If it's an open space
                    List<GraphNode> neighbors = new ArrayList<>();
                    // Check adjacent cells
                    if (i > 0 && !maze.isWall(currentPos.move(Direction.LEFT))) {
                        neighbors.add(new GraphNode(i - 1, j, false));
                    }
                    if (i < rows - 1 && !maze.isWall(currentPos.move(Direction.RIGHT))) {
                        neighbors.add(new GraphNode(i + 1, j, false));
                    }
                    if (j > 0 && !maze.isWall(currentPos.move(Direction.DOWN))) {
                        neighbors.add(new GraphNode(i, j - 1, false));
                    }
                    if (j < cols - 1 && !maze.isWall(currentPos.move(Direction.UP))) {
                        neighbors.add(new GraphNode(i, j + 1, false));
                    }
                    mazeGraph.put(new GraphNode(i, j, false), neighbors);
                }
            }
        }
        StartNode = StartNode(maze);
        EndNode = EndNode(maze);
    }

    public Map<GraphNode, List<GraphNode>> getMazeGraph() {
        return mazeGraph;
    }


    private GraphNode StartNode(Maze maze) throws Exception {
        for (int i = 0; i < maze.getSizeY(); i++) {
            Position pos = new Position(0, i);
            if (!maze.isWall(pos)) {
                return new GraphNode(0, i, false);
            }
        }
        throw new Exception("Invalid maze (no start position available)");
    }

    private GraphNode EndNode(Maze maze) throws Exception {
        for (int i = 0; i < maze.getSizeY(); i++) {
            Position pos = new Position(maze.getSizeX() - 1, i);
            if (!maze.isWall(pos)) {
                return new GraphNode(maze.getSizeX() - 1, i, false);
            }
        }
        throw new Exception("Invalid maze (no end position available)");
    }

    public GraphNode getStartNode() {
        return StartNode;
    }

    public GraphNode getEndNode() {
        return EndNode;
    }

    /**
     * Get start position.
     *
     * @return Start position
     */
    public Position getStartPos() {
        return new Position(StartNode.getX(), StartNode.getY());
    }

    /**
     * Get end position.
     *
     * @return End position
     */
    public Position getEndPos() { return new Position(EndNode.getX(), EndNode.getY()); }

}