package software.craftsmanship.debruijn.graphs.block;

import software.craftsmanship.debruijn.combinatorics.Word;

public interface VertexYieldBlock<T> {

    void yield(Word<T> word);

}
