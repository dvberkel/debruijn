package software.craftsmanship.debruijn.graphs.block;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import software.craftsmanship.debruijn.graphs.Edge;

public class EdgeCollectBlock<T> implements EdgeYieldBlock<T> {
    private final Set<Edge<T>> edges = new HashSet<Edge<T>>();

    @Override
    public void yield(Edge<T> edge) {
        edges.add(edge);
    }

    public Set<Edge<T>> edges() {
        return Collections.unmodifiableSet(edges);
    }

}
