package software.craftsmanship.debruijn.combinatorics;

public class CountBlock<T> implements CombinationYieldBlock<T> {

    private int count = 0;

    @Override
    public void yield(Combination<T> combination) {
        count++;
    }

    public Integer count() {
        return count;
    }

}
