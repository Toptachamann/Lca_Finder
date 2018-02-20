package com.timofey.chudakov;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.io.ImportException;

import java.io.IOException;

public interface GraphReader {
  Graph<String, DefaultEdge> readGraph(String path) throws IOException, ImportException;
}
