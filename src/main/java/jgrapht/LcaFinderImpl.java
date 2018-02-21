package jgrapht;

import org.jgrapht.Graph;
import org.jgrapht.alg.NaiveLcaFinder;
import org.jgrapht.graph.DefaultEdge;

import java.util.Set;

/**
 * Implementation of the interface <code>LcaFinder</code>
 *
 *
 */
public class LcaFinderImpl implements LcaFinder {
  /**
   * Implementation of the method for finding lowest common ancestors
   *
   * @implNote Finds lowest common ancestors using <code>NaiveLcaFinder</code> from JGraphT
   *
   * @param graph graph to perform search on
   * @param a first vertex to search from
   * @param b second vertex to search from
   * @return set of lowest common ancestors found by <code>NaiveLcaFinder</code>
   */
  @Override
  public Set<String> findLcas(Graph<String, DefaultEdge> graph, String a, String b) {
    NaiveLcaFinder<String, DefaultEdge> naiveLcaFinder = new NaiveLcaFinder<>(graph);
    return naiveLcaFinder.findLcas(a, b);
  }
}
