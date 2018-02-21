package jgrapht;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.jgrapht.io.DOTImporter;
import org.jgrapht.io.ImportException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/** Implementation of the interface <code>GraphReader</code> */
public class GraphReaderImpl implements GraphReader {
  /**
   * Implementation of the method for reading graph from file
   *
   * @implNote reads graph from specified file using <code>DOTImporter</code> from JGraphT
   * @param file file to read graph from
   * @return graph, that was read from the specified file
   * @throws IOException if the specified file is not valid or for some other reason cannot be read
   *     from
   * @throws ImportException if the format of the graph in the specified file is not valid
   */
  @Override
  public Graph<String, DefaultEdge> readGraph(File file) throws IOException, ImportException {
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      DOTImporter<String, DefaultEdge> importer =
          new DOTImporter<>((id, map) -> id, (from, to, label, map) -> new DefaultEdge());
      DirectedAcyclicGraph<String, DefaultEdge> graph =
          new DirectedAcyclicGraph<>(DefaultEdge.class);
      importer.importGraph(graph, reader);
      return graph;
    }
  }
}
