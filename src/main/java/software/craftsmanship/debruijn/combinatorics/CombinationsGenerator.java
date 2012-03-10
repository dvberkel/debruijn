package software.craftsmanship.debruijn.combinatorics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CombinationsGenerator<T> {
    public static <T> CombinationsGeneratorBuilder<T> allCombinationsOver(T... alphabet) {
        return new CombinationsGeneratorBuilder<T>(Arrays.asList(alphabet));
    }

    private final List<T> alphabet;
    private final int length;

    public CombinationsGenerator(List<T> alphabet, int length) {
        this.alphabet = copyOf(alphabet);
        this.length = length;

    }

    private List<T> copyOf(List<T> alphabet) {
        List<T> copy = new ArrayList<T>();
        copy.addAll(alphabet);
        return Collections.unmodifiableList(alphabet);
    }

    @SuppressWarnings("unchecked")
    public void each(CombinationYieldBlock<T> block) {
        // TODO: Implement this so the CombinationsGeneratorTest passes.
        block.yield((Combination<T>) Combination.empty());

    }
}

class CombinationsGeneratorBuilder<T> {

    private final List<T> alphabet;

    public CombinationsGeneratorBuilder(List<T> alphabet) {
        this.alphabet = alphabet;
    }

    public CombinationsGenerator<T> ofLength(int length) {
        return new CombinationsGenerator<T>(alphabet, length);
    }
}