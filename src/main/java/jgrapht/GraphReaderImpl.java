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
import java.util.Set;

public class GraphReaderImpl implements GraphReader {
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
