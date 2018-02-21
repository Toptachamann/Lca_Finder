package jgrapht;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.io.ImportException;

import java.io.File;
import java.io.IOException;

/** Defines a simple interface for reading graphs from file */
public interface GraphReader {
  /**
   * Method for reading a graph from file
   *
   * @param file file to read graph from
   * @return graph, that was read from the specified file
   * @throws IOException if the specified file is not valid or for some other reason cannot be read
   *     from
   * @throws ImportException if the format of the graph in the specified file is not valid
   */
  Graph<String, DefaultEdge> readGraph(File file) throws IOException, ImportException;
}
