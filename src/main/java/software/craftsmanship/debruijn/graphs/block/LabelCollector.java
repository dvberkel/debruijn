package software.craftsmanship.debruijn.graphs.block;

import static software.craftsmanship.debruijn.combinatorics.Word.empty;
import software.craftsmanship.debruijn.combinatorics.Word;
import software.craftsmanship.debruijn.graphs.Edge;

public class LabelCollector<T> implements EdgeYieldBlock<T> {

    private Word<T> word = empty();

    @Override
    public void yield(Edge<T> edge) {
        word = word.append(edge.label());
    }

    public Word<T> word() {
        return word;
    }

}
