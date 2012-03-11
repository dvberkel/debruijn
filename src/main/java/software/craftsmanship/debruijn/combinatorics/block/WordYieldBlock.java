package software.craftsmanship.debruijn.combinatorics.block;

import software.craftsmanship.debruijn.combinatorics.Word;

public interface WordYieldBlock<T> {
    public void yield(Word<T> word);
}
