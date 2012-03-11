package software.craftsmanship.debruijn.graphs;

import java.util.HashSet;
import java.util.Set;

import software.craftsmanship.debruijn.combinatorics.Word;
import software.craftsmanship.debruijn.graphs.block.EdgeYieldBlock;
import software.craftsmanship.debruijn.graphs.block.VertexYieldBlock;

public class Graph<T> {
    private final Set<Word<T>> vertices = new HashSet<Word<T>>();
    private final Set<Edge<T>> edges = new HashSet<Edge<T>>();

    public void addVertex(Word<T> word) {
        vertices.add(word);
    }

    public void allVertices(VertexYieldBlock<T> block) {
        for (Word<T> word : vertices) {
            block.yield(word);
        }
    }

    public EdgeBuilder<T> addEdgeFrom(Word<T> source) {
        return new EdgeBuilder<T>(this, source);
    }

    private void addEdge(Word<T> source, Word<T> sink) {
        edges.add(new Edge<T>(source, sink));
    }

    public void allEdges(EdgeYieldBlock<T> block) {
        for (Edge<T> edge : edges) {
            block.yield(edge);
        }
    }

    class EdgeBuilder<U> {
        private final Graph<U> graph;
        private final Word<U> source;

        public EdgeBuilder(Graph<U> graph, Word<U> source) {
            this.graph = graph;
            this.source = source;
        }

        public void to(Word<U> sink) {
            graph.addEdge(source, sink);
        }

    }
}
