package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class BFSSolver implements MazeSolver{
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Path solve(Maze maze) {
        public Path BFSSolver(GraphNode[][] graph, GraphNode start, GraphNode end) {
            // Queue for BFS traversal
            Queue<GraphNode> queue = new ArrayDeque<>();
            // Set to keep track of visited nodes
            Set<GraphNode> visited = new HashSet<>();
            // Map to store parent node for each node, for reconstructing the path
            // Key: child node, Value: parent node
            java.util.Map<GraphNode, GraphNode> parentMap = new java.util.HashMap<>();

            // Add the start node to the queue and mark it as visited
            queue.offer(start);
            visited.add(start);

            // Perform BFS traversal
            while (!queue.isEmpty()) {
                GraphNode currentNode = queue.poll();
                if (currentNode == end) {
                    // End node reached, reconstruct the path and return it
                    return reconstructPath(parentMap, start, end);
                }

                // Explore neighbors of the current node
                for (GraphNode neighbor : getNeighbors(graph, currentNode)) {
                    if (!visited.contains(neighbor)) {
                        queue.offer(neighbor);
                        visited.add(neighbor);
                        // Record parent node for each neighbor
                        parentMap.put(neighbor, currentNode);
                    }
                }
            }

            // If end node is not reachable from the start node
            return new ArrayList<>();
        }

        private static List<GraphNode> getNeighbors(GraphNode[][] graph, GraphNode node) {
            List<GraphNode> neighbors = new ArrayList<>();
            int numRows = graph.length;
            int numCols = graph[0].length;
            int x = node.getX();
            int y = node.getY();

            // Add valid neighbors (adjacent nodes that are not walls)
            if (x > 0 && !graph[x - 1][y].isWall()) {
                neighbors.add(graph[x - 1][y]);
            }
            if (x < numRows - 1 && !graph[x + 1][y].isWall()) {
                neighbors.add(graph[x + 1][y]);
            }
            if (y > 0 && !graph[x][y - 1].isWall()) {
                neighbors.add(graph[x][y - 1]);
            }
            if (y < numCols - 1 && !graph[x][y + 1].isWall()) {
                neighbors.add(graph[x][y + 1]);
            }

            return neighbors;
        }

        private static List<GraphNode> reconstructPath(java.util.Map<GraphNode, GraphNode> parentMap, GraphNode start, GraphNode end) {
            List<GraphNode> path = new ArrayList<>();
            GraphNode currentNode = end;
            // Trace back the path from end node to start node using parentMap
            while (currentNode != start) {
                path.add(currentNode);
                currentNode = parentMap.get(currentNode);
            }
            // Add the start node
            path.add(start);
            // Reverse the path to get the correct order
            java.util.Collections.reverse(path);
            return path;
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
