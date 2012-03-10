package software.craftsmanship.debruijn.combinatorics;

import java.util.HashSet;
import java.util.Set;

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
