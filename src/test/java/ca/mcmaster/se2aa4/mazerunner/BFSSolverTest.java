package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class BFSSolverTest {
    @Test
    void testSolve() {
        try {
            // Create a sample maze
            Maze maze = new Maze("C:direct.maz.txt");
            maze.setWall(1, 1, true); // Set a wall at position (1,1)
            maze.setWall(3, 3, true); // Set a wall at position (3,3)

            // Create a BFSSolver object
            BFSSolver bfsSolver = new BFSSolver();

            // Solve the maze
            Path path = bfsSolver.solve(maze);

            // Expected path (assuming no walls)
            String expectedPath = "FFFFF";

            // Assert that the solved path matches the expected path
            assertEquals(expectedPath, path.getFactorizedForm());
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}
