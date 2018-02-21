package jgrapht;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.io.ImportException;

import java.io.File;
import java.io.IOException;

public interface GraphReader {
  Graph<String, DefaultEdge> readGraph(File file) throws IOException, ImportException;
}