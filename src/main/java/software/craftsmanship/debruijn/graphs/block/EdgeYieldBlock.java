package software.craftsmanship.debruijn.graphs.block;

import software.craftsmanship.debruijn.graphs.Edge;

public interface EdgeYieldBlock<T> {

    void yield(Edge<T> edge);

}
