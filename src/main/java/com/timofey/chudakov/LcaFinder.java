package com.timofey.chudakov;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.Set;

public interface LcaFinder {
  Set<String> findLcas(Graph<String, DefaultEdge> graph, String a, String b);
}
