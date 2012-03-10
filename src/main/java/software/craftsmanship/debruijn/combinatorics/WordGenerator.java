package software.craftsmanship.debruijn.combinatorics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WordGenerator<T> {
    public static <T> WordGeneratorBuilder<T> allWordsOver(T... alphabet) {
        return new WordGeneratorBuilder<T>(Arrays.asList(alphabet));
    }

    private final List<T> alphabet;
    private final int length;

    private WordGenerator(List<T> alphabet, int length) {
        this.alphabet = copyOf(alphabet);
        this.length = length;

    }

    private List<T> copyOf(List<T> alphabet) {
        List<T> copy = new ArrayList<T>();
        copy.addAll(alphabet);
        return Collections.unmodifiableList(alphabet);
    }

    @SuppressWarnings("unchecked")
    public void each(WordYieldBlock<T> block) {
        // TODO: Implement this so the CombinationsGeneratorTest passes.
        block.yield((Word<T>) Word.empty());

    }

    static class WordGeneratorBuilder<T> {

        private final List<T> alphabet;

        public WordGeneratorBuilder(List<T> alphabet) {
            this.alphabet = alphabet;
        }

        public WordGenerator<T> ofLength(int length) {
            return new WordGenerator<T>(alphabet, length);
        }
    }
}
