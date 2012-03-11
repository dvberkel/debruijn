package software.craftsmanship.debruijn.graphs.block;

import software.craftsmanship.debruijn.graphs.Edge;

public class EdgeCountBlock<T> implements EdgeYieldBlock<T> {

    private int count = 0;

    @Override
    public void yield(Edge<T> edge) {
        count++;
    }

    public Integer count() {
        return count;
    }

}
