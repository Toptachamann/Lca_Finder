package jgrapht;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.io.ImportException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class GraphReaderImplTest {
  private GraphReaderImpl reader;

  public GraphReaderImplTest() {
    reader = new GraphReaderImpl();
  }

  @Test
  public void graphReaderImplTest() throws IOException, ImportException {
    File file = new File("src/test/resources/test.dot");
    Graph<String, DefaultEdge> graph = reader.readGraph(file);
    assertEquals("Size should be equal to 4", 4, graph.vertexSet().size());
    assertEquals("Size should be equal to 4", 4, graph.edgeSet().size());
    assertTrue(graph.containsVertex("Father"));
    assertTrue(graph.containsVertex("Mother"));
    assertTrue(graph.containsVertex("Sister"));
    assertTrue(graph.containsVertex("Brother"));
    assertTrue(graph.containsEdge("Father", "Brother"));
    assertTrue(graph.containsEdge("Father", "Sister"));
    assertTrue(graph.containsEdge("Mother", "Sister"));
    assertTrue(graph.containsEdge("Mother", "Brother"));
  }
}