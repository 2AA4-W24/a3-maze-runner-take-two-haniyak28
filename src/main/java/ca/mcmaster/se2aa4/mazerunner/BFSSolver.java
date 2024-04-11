package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.*;
public class BFSSolver implements MazeSolver{
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Path solve(Maze maze) {

            public static Path BFSSolver(GraphNode start, GraphNode end) {
                Map<GraphNode, GraphNode> parentMap = new HashMap<>();
                Queue<GraphNode> queue = new LinkedList<>();
                Set<GraphNode> visited = new HashSet<>();

                queue.offer(start);
                visited.add(start);

                while (!queue.isEmpty()) {
                    GraphNode current = queue.poll();

                    if (current.equals(end)) {
                        return reconstructPath(parentMap, start, end);
                    }

                    for (GraphNode neighbor : current.getNeighbors()) {
                        if (!visited.contains(neighbor)) {
                            queue.offer(neighbor);
                            visited.add(neighbor);
                            parentMap.put(neighbor, current);
                        }
                    }
                }

                return ""; // If no path is found
            }

            private static String reconstructPath(Map<GraphNode, GraphNode> parentMap, GraphNode start, GraphNode end) {
                StringBuilder path = new StringBuilder();
                GraphNode current = end;

                while (!current.equals(start)) {
                    GraphNode parent = parentMap.get(current);
                    path.insert(0, getDirection(parent, current));
                    current = parent;
                }

                return path.toString();
            }

            private static char getDirection(GraphNode from, GraphNode to) {
                if (from.getX() == to.getX()) {
                    return from.getY() < to.getY() ? 'R' : 'L';
                } else {
                    return from.getX() < to.getX() ? 'F' : 'B';
                }
            }

    }
}


/*package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;

public class BFSSolver implements MazeSolver {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Path solve(Maze maze) {
        Path path = new Path();
        Position start = maze.getStart();
        Position end = maze.getEnd();

        Queue<Position> queue = new LinkedList<>();
        boolean[][] visited = new boolean[maze.getSizeY()][maze.getSizeX()];
        Position[][] parent = new Position[maze.getSizeY()][maze.getSizeX()];

        // Add start position to the queue and mark it as visited
        queue.add(start);
        visited[start.y()][start.x()] = true;

        while (!queue.isEmpty()) {
            Position current = queue.poll();

            // Check if the current position is the exit
            if (current.equals(end)) {
                // Construct the path by backtracking from the end position
                // using the parent array
                while (!current.equals(start)) {
                    path.addStep(parent[current.y()][current.x()].getDirection(current));
                    current = parent[current.y()][current.x()];
                }
                // Reverse the path to get it from start to end
                path.reverse();
                return path;
            }

            // Explore all adjacent positions
            for (Direction dir : Direction.values()) {
                Position next = current.move(dir);
                if (!maze.isWall(next) && !visited[next.y()][next.x()]) {
                    visited[next.y()][next.x()] = true;
                    queue.add(next);
                    parent[next.y()][next.x()] = current;
                }
            }
        }

        // If no path is found, return an empty path
        return path;
    }
}*/
