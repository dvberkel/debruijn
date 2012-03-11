package software.craftsmanship.debruijn.combinatorics.block;

import software.craftsmanship.debruijn.combinatorics.Word;

public class CountBlock<T> implements WordYieldBlock<T> {

    private int count = 0;

    @Override
    public void yield(Word<T> word) {
        count++;
    }

    public Integer count() {
        return count;
    }

}
