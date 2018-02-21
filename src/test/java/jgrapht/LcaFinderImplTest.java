package jgrapht;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LcaFinderImplTest {

  private LcaFinderImpl finder;

  public LcaFinderImplTest() {
    finder = new LcaFinderImpl();
  }

  @Test
  public void basicLcaFinderTest() {
    DirectedAcyclicGraph<String, DefaultEdge> graph = new DirectedAcyclicGraph<>(DefaultEdge.class);
    graph.addVertex("Father");
    graph.addVertex("Mother");
    graph.addVertex("Sister");
    graph.addVertex("Brother");
    graph.addEdge("Father", "Brother");
    graph.addEdge("Father", "Sister");
    graph.addEdge("Mother", "Brother");
    graph.addEdge("Mother", "Sister");
    Set<String> lcas = finder.findLcas(graph, "Sister", "Brother");
    assertTrue("Lcas should contain vertex 'Mother'", lcas.contains("Mother"));
    assertTrue("Lcas should contain vertex 'Father'", lcas.contains("Father"));
    assertEquals("Size of lcas should be equal to 2", 2, lcas.size());
  }

  @Test
  public void bigLcaFinderTest() {
    DirectedAcyclicGraph<String, DefaultEdge> graph = new DirectedAcyclicGraph<>(DefaultEdge.class);
    graph.addVertex("1");
    graph.addVertex("2");
    graph.addVertex("3");
    graph.addVertex("4");
    graph.addVertex("5");
    graph.addVertex("6");
    graph.addVertex("7");
    graph.addVertex("8");
    graph.addVertex("9");
    graph.addVertex("10");
    graph.addVertex("11");
    graph.addVertex("12");
    graph.addVertex("13");
    graph.addEdge("1", "2");
    graph.addEdge("1", "3");
    graph.addEdge("2", "4");
    graph.addEdge("2", "5");
    graph.addEdge("2", "6");
    graph.addEdge("5", "7");
    graph.addEdge("6", "8");
    graph.addEdge("7", "9");
    graph.addEdge("7", "10");
    graph.addEdge("11", "12");
    graph.addEdge("11", "13");
    graph.addEdge("12", "7");
    graph.addEdge("13", "8");
    Set<String> lcas1 = finder.findLcas(graph, "3", "4");
    Set<String> lcas2 = finder.findLcas(graph, "7", "8");
    Set<String> lcas3 = finder.findLcas(graph, "9", "8");
    assertEquals(1, lcas1.size());
    assertEquals(2, lcas2.size());
    assertEquals(2, lcas3.size());
    assertTrue("Should contain [1]", lcas1.containsAll(Collections.singletonList("1")));
    assertTrue("Should contain [2, 11]", lcas2.containsAll(Arrays.asList("2", "11")));
    assertTrue("Should contain [2]", lcas3.containsAll(Arrays.asList("2", "11")));
  }
}
