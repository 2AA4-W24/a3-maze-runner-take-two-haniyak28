package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class GraphNodeTest {

    @Test
    void testConstructorAndGetters() {
        GraphNode node = new GraphNode(1, 2, false);
        assertEquals(1, node.getX());
        assertEquals(2, node.getY());
        assertFalse(node.isWall());
    }

    @Test
    void testAddAndGetNeighbors() {
        GraphNode node1 = new GraphNode(1, 2, false);
        GraphNode node2 = new GraphNode(2, 2, false);
        GraphNode node3 = new GraphNode(1, 3, false);

        node1.addNeighbor(node2);
        node1.addNeighbor(node3);

        List<GraphNode> neighbors = node1.getNeighbors();
        assertTrue(neighbors.contains(node2));
        assertTrue(neighbors.contains(node3));
        assertEquals(2, neighbors.size());
    }

    @Test
    void testHasNeighbor() {
        GraphNode node1 = new GraphNode(1, 2, false);
        GraphNode node2 = new GraphNode(2, 2, false);
        GraphNode node3 = new GraphNode(1, 3, false);

        node1.addNeighbor(node2);
        node1.addNeighbor(node3);

        assertTrue(node1.hasNeighbor(node2));
        assertTrue(node1.hasNeighbor(node3));
    }
}