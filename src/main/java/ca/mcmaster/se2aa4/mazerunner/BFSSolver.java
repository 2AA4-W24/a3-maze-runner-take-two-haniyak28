package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.*;
public class BFSSolver implements MazeSolver{
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Path solve(Maze mazeList) throws Exception {

        MazeGraph maze = new MazeGraph(mazeList);

            //public static Path BFSSolver(GraphNode start, GraphNode end) {
        Map<GraphNode, GraphNode> parentMap = new HashMap<>();
        Map<GraphNode, Integer> distanceMap = new HashMap<>();
        Queue<GraphNode> queue = new LinkedList<>();
        Set<GraphNode> visited = new HashSet<>();

        queue.offer(maze.getStartNode());
        visited.add(maze.getStartNode());
        distanceMap.put(maze.getStartNode(), 0);

        while (!queue.isEmpty()) {
            GraphNode current = queue.poll();

            if (current.equals(maze.getEndNode())) {
                Path path = new Path(reconstructPath(parentMap, maze.getStartNode(), maze.getEndNode()));
                logger.debug("Current Path: " + path.getCanonicalForm());
                return path;
                //return reconstructPath(parentMap, maze.getStartNode(), maze.getEndNode());
            }

            for (GraphNode neighbor : current.getNeighbors()) {
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    // Update the distance of the neighbor node
                    distanceMap.put(neighbor, distanceMap.get(current) + 1);
                }
            }
        }
        return new Path("");
    }

    private String reconstructPath(Map<GraphNode, GraphNode> parentMap, GraphNode start, GraphNode end) {
        StringBuilder path = new StringBuilder();
        GraphNode current = end; // Start from the end node

        // Traverse the parent map from end to start
        while (!current.equals(start)) {
            GraphNode parent = parentMap.get(current);
            path.insert(0, getDirection(parent, current)); // Insert the direction at the beginning of the path
            current = parent; // Move to the parent node
        }

        return path.toString();
    }

    private char getDirection(GraphNode from, GraphNode to) {
        if (from.getX() == to.getX()) {
            return from.getY() < to.getY() ? 'R' : 'L';
        } else {
            return from.getX() < to.getX() ? 'F' : 'B';
        }
    }
}
