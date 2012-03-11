package software.craftsmanship.debruijn.combinatorics.block;

import java.util.HashSet;
import java.util.Set;

import software.craftsmanship.debruijn.combinatorics.Word;

public class CollectBlock<T> implements WordYieldBlock<T> {

    private final Set<Word<T>> words = new HashSet<Word<T>>();

    @Override
    public void yield(Word<T> word) {
        words.add(word);
    }

    public Set<Word<T>> words() {
        return this.words;
    }

}
