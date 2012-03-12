package software.craftsmanship.debruijn.combinatorics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import software.craftsmanship.debruijn.combinatorics.block.WordYieldBlock;

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
    public void each(final WordYieldBlock<T> block) {
	if (length == 0) {
	    block.yield((Word<T>) Word.empty());
	} else {
	    new WordGenerator<T>(alphabet, length - 1).each(new WordYieldBlock<T>() {

		@Override
		public void yield(Word<T> word) {
		    for (T letter : alphabet) {
			block.yield(word.append(letter));
		    }
		    
		}
	    });
	}
    }

    public static class WordGeneratorBuilder<T> {

        private final List<T> alphabet;

        public WordGeneratorBuilder(List<T> alphabet) {
            this.alphabet = alphabet;
        }

        public WordGenerator<T> ofLength(int length) {
            return new WordGenerator<T>(alphabet, length);
        }
    }
}
