package jgrapht;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.Set;

/**
 * Defines a simple interface for finding lowest common ancestors in the <code>graph</code> of the
 * vertices <code>a</code> and <code>b</code>
 */
public interface LcaFinder {
  /**
   * Method for finding lowest common ancestors
   *
   * @param graph graph to perform search on
   * @param a first vertex to search from
   * @param b second vertex to search from
   * @return set of the found lowest common ancestors
   */
  Set<String> findLcas(Graph<String, DefaultEdge> graph, String a, String b);
}
