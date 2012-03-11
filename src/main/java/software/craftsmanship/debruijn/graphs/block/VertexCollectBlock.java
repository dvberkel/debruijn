package software.craftsmanship.debruijn.graphs.block;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import software.craftsmanship.debruijn.combinatorics.Word;

public class VertexCollectBlock<T> implements VertexYieldBlock<T> {
    Set<Word<T>> vertices = new HashSet<Word<T>>();

    @Override
    public void yield(Word<T> word) {
        vertices.add(word);
    }

    public Set<Word<T>> vertices() {
        return Collections.unmodifiableSet(vertices);
    }

}
