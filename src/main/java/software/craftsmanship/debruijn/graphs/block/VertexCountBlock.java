package software.craftsmanship.debruijn.graphs.block;

import software.craftsmanship.debruijn.combinatorics.Word;

public class VertexCountBlock<T> implements VertexYieldBlock<T> {

    private int count = 0;

    @Override
    public void yield(Word<T> word) {
        count++;
    }

    public Integer count() {
        return count;
    }

}
