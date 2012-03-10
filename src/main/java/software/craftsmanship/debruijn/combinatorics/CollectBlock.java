package software.craftsmanship.debruijn.combinatorics;

import java.util.HashSet;
import java.util.Set;

public class CollectBlock<T> implements CombinationYieldBlock<T> {

    private final Set<Combination<T>> combinations = new HashSet<Combination<T>>();

    @Override
    public void yield(Combination<T> combination) {
        combinations.add(combination);
    }

    public Set<Combination<T>> combinations() {
        return this.combinations;
    }

}
