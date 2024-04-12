package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MazeGraphTest {
    @Test
    void testConstructorAndGetters() throws Exception {
        // Create a sample maze
        Maze maze = new Maze("C:direct.maz.txt");
        maze.setWall(1, 1, true); // Set a wall at position (1, 1)
        maze.setWall(3, 3, true); // Set a wall at position (3, 3)

        try {
            // Create a MazeGraph object
            MazeGraph mazeGraph = new MazeGraph(maze);

            // Test start node
            GraphNode startNode = mazeGraph.getStartNode();
            assertEquals(0, startNode.getX());
            assertEquals(0, startNode.getY());

            // Test end node
            GraphNode endNode = mazeGraph.getEndNode();
            assertEquals(4, endNode.getX());
            assertEquals(0, endNode.getY());

            // Test maze graph
            assertEquals(22, mazeGraph.getMazeGraph().size()); // Number of nodes in the maze
            assertTrue(mazeGraph.getMazeGraph().containsKey(startNode));
            assertTrue(mazeGraph.getMazeGraph().containsKey(endNode));
            assertFalse(mazeGraph.getMazeGraph().get(startNode).isEmpty());
            assertFalse(mazeGraph.getMazeGraph().get(endNode).isEmpty());
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}
