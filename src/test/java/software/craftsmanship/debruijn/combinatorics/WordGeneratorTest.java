package software.craftsmanship.debruijn.combinatorics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static software.craftsmanship.debruijn.combinatorics.Word.word;
import static software.craftsmanship.debruijn.combinatorics.Word.empty;
import static software.craftsmanship.debruijn.combinatorics.WordGenerator.allWordsOver;

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
public class WordGeneratorTest<T> {
    private final T[] alphabet;
    private final int length;
    private final Integer expectedCount;
    private WordGenerator<T> generator;
    private final Set<Word<T>> expectedWords;

    public WordGeneratorTest(ParametersBuilder<T> builder) {
        this.alphabet = builder.alphabet;
        this.length = builder.length;
        this.expectedCount = builder.words.size();
        this.expectedWords = builder.words;
    }

    @Before
    public void createGenerator() {
        this.generator = allWordsOver(alphabet).ofLength(length);
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

        assertEquals(expectedWords, collector.words());
    }

    @SuppressWarnings("unchecked")
    @Parameters
    public static Collection<Object[]> data() {
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        data.add(alphabet("a").length(0).expected(empty(String.class)).build());
        data.add(alphabet("a").length(1).expected(word("a")).build());
        data.add(alphabet("a").length(2).expected(word("aa")).build());
        data.add(alphabet("a", "b").length(0).expected(empty(String.class)).build());
        data.add(alphabet("a", "b").length(1).expected(word("a"), word("b")).build());
        data.add(alphabet("a", "b").length(2)
                .expected(word("aa"), word("ab"), word("ba"), word("bb")).build());
        return data;
    }

    public static <U> ParametersBuilder<U> alphabet(U... alphabet) {
        return new ParametersBuilder<U>(alphabet);
    }
}

class ParametersBuilder<T> {

    public final T[] alphabet;
    public int length;
    public Set<Word<T>> words;

    public ParametersBuilder(T[] alphabet) {
        this.alphabet = alphabet;
    }

    public ParametersBuilder<T> length(int length) {
        this.length = length;
        return this;
    }

    public ParametersBuilder<T> expected(Word<T>... words) {
        this.words = new HashSet<Word<T>>();
        for (Word<T> word : words) {
            this.words.add(word);
        }
        return this;
    }

    public Object[] build() {
        return new Object[]{this};
    }
}
