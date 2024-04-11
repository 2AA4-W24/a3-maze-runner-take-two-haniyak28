package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class GraphNode {
    private int x;
    private int y;
    private boolean isWall;
    private List<GraphNode> neighbors;

    public GraphNode(int x, int y, Boolean isWall) {
        this.x = x;
        this.y = y;
        this.isWall = isWall;
        this.neighbors = new ArrayList<>();
    }

    // Getters and setters
    // Methods to add and get neighbors
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

    public List<GraphNode> getNeighbors() {
        return neighbors;
    }

    // Methods to add and get neighbors
    public void addNeighbor(GraphNode neighbor) {
        neighbors.add(neighbor);
    }

    public boolean hasNeighbor(GraphNode neighbor) {
        return neighbors.contains(neighbor);
    }
}
