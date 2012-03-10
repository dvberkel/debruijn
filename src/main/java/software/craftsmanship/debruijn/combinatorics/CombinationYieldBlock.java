package software.craftsmanship.debruijn.combinatorics;

public interface CombinationYieldBlock<T> {
    public void yield(Combination<T> combination);
}
