package jgrapht

import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.DirectedAcyclicGraph
import spock.lang.Specification
import spock.lang.Unroll

class GroovyLcaFinderImplTest extends Specification {
  def "Basic LcaFinder test"() {
    def finder = new LcaFinderImpl()
    def graph = new DirectedAcyclicGraph(DefaultEdge.class)
    graph.addVertex("Father")
    graph.addVertex("Mother")
    graph.addVertex("Sister")
    graph.addVertex("Brother")
    graph.addEdge("Father", "Sister")
    graph.addEdge("Father", "Brother")
    graph.addEdge("Mother", "Sister")
    graph.addEdge("Mother", "Brother")
    def set = finder.findLcas(graph, "Brother", "Sister")
    expect:
    set.containsAll(["Father", "Mother"])
  }

  @Unroll
  def "Big LcaFinder test"() {
    def finder = new LcaFinderImpl()
    def graph = new DirectedAcyclicGraph(DefaultEdge.class)
    graph.addVertex("A")
    graph.addVertex("B")
    graph.addVertex("C")
    graph.addVertex("D")
    graph.addVertex("E")
    graph.addVertex("F")
    graph.addVertex("G")
    graph.addVertex("H")
    graph.addVertex("I")
    graph.addVertex("J")
    graph.addVertex("K")
    graph.addVertex("L")
    graph.addEdge("A", "B")
    graph.addEdge("A", "C")
    graph.addEdge("B", "D")
    graph.addEdge("B", "E")
    graph.addEdge("E", "H")
    graph.addEdge("E", "J")
    graph.addEdge("C", "F")
    graph.addEdge("C", "G")
    graph.addEdge("F", "H")
    graph.addEdge("F", "J")
    graph.addEdge("F", "K")
    graph.addEdge("G", "L")
    def set = finder.findLcas(graph, a, b)
    expect:
    set.containsAll(result)
    where:
    a   | b   | result
    "D" | "C" | ["A"]
    "H" | "J" | ["E", "F"]
    "H" | "L" | ["C"]
  }
}
