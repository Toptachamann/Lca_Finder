package com.timofey.chudakov;

import org.jgrapht.Graph;
import org.jgrapht.alg.NaiveLcaFinder;
import org.jgrapht.graph.DefaultEdge;

import java.util.Set;

public class LcaFinderImpl implements LcaFinder {
  @Override
  public Set<String> findLcas(Graph<String, DefaultEdge> graph, String a, String b) {
    NaiveLcaFinder<String, DefaultEdge> naiveLcaFinder = new NaiveLcaFinder<>(graph);
    return naiveLcaFinder.findLcas(a, b);
  }
}
