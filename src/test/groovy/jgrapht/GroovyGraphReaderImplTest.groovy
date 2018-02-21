package jgrapht

import spock.lang.Specification

class GroovyGraphReaderImplTest extends Specification {

  def "GraphReaderImpl test"(){
    def reader = new GraphReaderImpl()
    def file = new File("src/test/resources/groovyTest.dot")
    def graph = reader.readGraph(file)
    expect:
    graph.vertexSet().size() == 5
    graph.edgeSet().size() == 6
    graph.containsVertex("A")
    graph.containsVertex("B")
    graph.containsVertex("C")
    graph.containsVertex("D")
    graph.containsVertex("E")
    graph.containsEdge("A", "B")
    graph.containsEdge("B", "D")
    graph.containsEdge("A", "D")
    graph.containsEdge("A", "E")
    graph.containsEdge("A", "C")
    graph.containsEdge("C", "E")
  }
}
