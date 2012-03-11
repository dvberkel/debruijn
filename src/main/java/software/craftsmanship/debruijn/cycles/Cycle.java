package software.craftsmanship.debruijn.cycles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    private static <U> Set<Edge<U>> copyOf(Set<Edge<U>> aSet) {
        Set<Edge<U>> copy = new HashSet<Edge<U>>();
        copy.addAll(aSet);
        return copy;
    }

    private final List<Edge<T>> cycle = new ArrayList<Edge<T>>();
    private final Set<Edge<T>> edges;
    private final Word<T> start;

    private Cycle(Set<Edge<T>> edges, Word<T> start) {
        this.edges = copyOf(edges);
        this.start = start;
        findACycleAmongEdgesFromStart();
    }

    private void findACycleAmongEdgesFromStart() {
        // TODO: implement this to make the CycleTest pass.
    }

    public void allEdges(EdgeYieldBlock<T> block) {
        for (Edge<T> edge : cycle) {
            block.yield(edge);
        }
    }

    public static class CycleBuilder<T> {
        private final Set<Edge<T>> edges;

        public CycleBuilder(Set<Edge<T>> edgesOfGraph) {
            this.edges = copyOf(edgesOfGraph);
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
