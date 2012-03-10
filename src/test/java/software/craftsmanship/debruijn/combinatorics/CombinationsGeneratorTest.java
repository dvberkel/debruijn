package software.craftsmanship.debruijn.combinatorics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static software.craftsmanship.debruijn.combinatorics.Combination.combination;
import static software.craftsmanship.debruijn.combinatorics.Combination.empty;
import static software.craftsmanship.debruijn.combinatorics.CombinationsGenerator.allCombinationsOver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CombinationsGeneratorTest<T> {
    private final T[] alphabet;
    private final int length;
    private final Integer expectedCount;
    private CombinationsGenerator<T> generator;
    private final Set<Combination<T>> expectedCombinations;

    public CombinationsGeneratorTest(ParametersBuilder<T> builder) {
        this.alphabet = builder.alphabet;
        this.length = builder.length;
        this.expectedCount = builder.combinations.size();
        this.expectedCombinations = builder.combinations;
    }

    @Before
    public void createGenerator() {
        this.generator = allCombinationsOver(alphabet).ofLength(length);
    }

    @Test
    public void shouldHaveCreatedAGenerator() {
        assertNotNull(generator);
    }

    @Test
    public void shouldAcceptACountBlock() {
        CountBlock<T> counter = new CountBlock<T>();

        generator.each(counter);

        assertEquals(expectedCount, counter.count());
    }

    @Test
    public void shouldAcceptACollectBlock() {
        CollectBlock<T> collector = new CollectBlock<T>();

        generator.each(collector);

        assertEquals(expectedCombinations, collector.combinations());
    }

    @SuppressWarnings("unchecked")
    @Parameters
    public static Collection<Object[]> data() {
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        data.add(alphabet("a").length(0).expected(empty(String.class)).build());
        data.add(alphabet("a").length(1).expected(combination("a")).build());
        data.add(alphabet("a").length(2).expected(combination("aa")).build());
        data.add(alphabet("a", "b").length(0).expected(empty(String.class)).build());
        data.add(alphabet("a", "b").length(1).expected(combination("a"), combination("b")).build());
        data.add(alphabet("a", "b").length(2)
                .expected(combination("aa"), combination("ab"), combination("ba"), combination("bb")).build());
        return data;
    }

    public static <U> ParametersBuilder<U> alphabet(U... alphabet) {
        return new ParametersBuilder<U>(alphabet);
    }
}

class ParametersBuilder<T> {

    public final T[] alphabet;
    public int length;
    public Set<Combination<T>> combinations;

    public ParametersBuilder(T[] alphabet) {
        this.alphabet = alphabet;
    }

    public ParametersBuilder<T> length(int length) {
        this.length = length;
        return this;
    }

    public ParametersBuilder<T> expected(Combination<T>... combinations) {
        this.combinations = new HashSet<Combination<T>>();
        for (Combination<T> combination : combinations) {
            this.combinations.add(combination);
        }
        return this;
    }

    public Object[] build() {
        return new Object[]{this};
    }
}
