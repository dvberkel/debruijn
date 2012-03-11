package software.craftsmanship.debruijn.cycles;

import java.util.HashSet;
import java.util.Set;

import software.craftsmanship.debruijn.combinatorics.Word;
import software.craftsmanship.debruijn.graphs.Edge;
import software.craftsmanship.debruijn.graphs.Graph;
import software.craftsmanship.debruijn.graphs.block.EdgeCollectBlock;
import software.craftsmanship.debruijn.graphs.block.EdgeYieldBlock;

public class Cycle<T> {
    public static <U> CycleBuilder<U> cycleOver(Graph<U> graph) {
        EdgeCollectBlock<U> collector = new EdgeCollectBlock<U>();
        graph.allEdges(collector);
        return new CycleBuilder<U>(collector.edges());
    }

    private Cycle(Set<Edge<T>> edges, Word<T> start) {
        // TODO Auto-generated constructor stub
    }

    public void allEdges(EdgeYieldBlock<T> edgeYieldBlock) {}

    public static class CycleBuilder<T> {
        private final Set<Edge<T>> edges;

        public CycleBuilder(Set<Edge<T>> edgesOfGraph) {
            Set<Edge<T>> edges = new HashSet<Edge<T>>();
            edges.addAll(edgesOfGraph);
            this.edges = edges;
        }

        public CycleBuilder<T> avoiding(Cycle<T> cycle) {
            EdgeCollectBlock<T> collector = new EdgeCollectBlock<T>();
            cycle.allEdges(collector);
            return avoiding(collector.edges());

        }

        public CycleBuilder<T> avoiding(Set<Edge<T>> otherEdges) {
            edges.removeAll(otherEdges);
            return this;

        }

        public Cycle<T> startingAt(Word<T> start) {
            return new Cycle<T>(edges, start);
        }

    }
}
