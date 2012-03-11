package software.craftsmanship.debruijn.cycles;

import static org.junit.Assert.assertEquals;
import static software.craftsmanship.debruijn.combinatorics.Word.empty;
import static software.craftsmanship.debruijn.combinatorics.Word.word;
import static software.craftsmanship.debruijn.cycles.Cycle.cycleOver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import software.craftsmanship.debruijn.combinatorics.Word;
import software.craftsmanship.debruijn.graphs.Edge;
import software.craftsmanship.debruijn.graphs.Graph;
import software.craftsmanship.debruijn.graphs.block.EdgeYieldBlock;

@RunWith(Parameterized.class)
public class CycleTest<T> {
    private final Graph<T> graph;
    private final Word<T> start;
    private final Word<T> expectedWord;
    private final Set<Edge<T>> avoidedEdges;

    public CycleTest(Graph<T> graph, Word<T> start, Word<T> expectedWord, Set<Edge<T>> avoidedEdges) {
        this.graph = graph;
        this.start = start;
        this.expectedWord = expectedWord;
        this.avoidedEdges = avoidedEdges;
    }

    @Test
    public void shouldCreateACorrectCylceOverAGraph() {
        Cycle<T> cycle = cycleOver(graph).startingAt(start);
        CycleWordCollector<T> collector = new CycleWordCollector<T>();

        cycle.allEdges(collector);

        assertEquals(expectedWord, collector.word());
    }

    @Test
    public void shouldCreateACorrectCylceOverAGraphAvoidingEdges() {
        Cycle<T> cycle = cycleOver(graph).avoiding(avoidedEdges).startingAt(start);
        CycleWordCollector<T> collector = new CycleWordCollector<T>();

        cycle.allEdges(collector);

        assertEquals(expectedWord, collector.word());
    }

    @Parameters
    public static Collection<Object[]> data() {
        List<Object[]> data = new ArrayList<Object[]>();
        data.add(new Object[]{singletonGraph("a"), word("a"), word("a"), Collections.emptySet()});
        data.add(new Object[]{singletonGraph("b"), word("b"), word("b"), Collections.emptySet()});
        data.add(new Object[]{cyclicGraph("a", "b"), word("a"), word("a", "b"), Collections.emptySet()});
        data.add(new Object[]{cyclicGraph("a", "b", "c"), word("a"), word("a", "b", "c"), Collections.emptySet()});
        data.add(new Object[]{cyclicGraph("a", "b", "c", "d"), word("a"), word("a", "b", "c", "d"),
                Collections.emptySet()});
        data.add(new Object[]{bulgeGraph("a", "b"), word("a"), word("a", "b"), loop("a")});
        data.add(new Object[]{bulgeGraph("a", "b", "c"), word("a"), word("a", "b", "c"), loop("a")});
        data.add(new Object[]{bulgeGraph("a", "b", "c", "d"), word("a"), word("a", "b", "c", "d"), loop("a")});
        return data;
    }

    private static <T> Graph<T> singletonGraph(T letter) {
        @SuppressWarnings("unchecked")
        Word<T> word = word(letter);
        Graph<T> graph = new Graph<T>();
        graph.addVertex(word);
        graph.addEdgeFrom(word).to(word).label(letter);
        return graph;
    }

    @SuppressWarnings("unchecked")
    private static <T> Graph<T> cyclicGraph(T... letters) {
        Graph<T> graph = new Graph<T>();
        for (T letter : letters) {
            Word<T> word = word(letter);
            graph.addVertex(word);
        }
        for (int index = 0; index < letters.length - 1; index++) {
            Word<T> source = word(letters[index]);
            Word<T> sink = word(letters[index + 1]);
            graph.addEdgeFrom(sink).to(source).label(letters[index]);
        }
        T lastLetter = letters[letters.length - 1];
        graph.addEdgeFrom(word(lastLetter)).to(word(letters[0])).label(lastLetter);
        return graph;
    }

    @SuppressWarnings("unchecked")
    private static <T> Graph<T> bulgeGraph(T... letters) {
        Graph<T> graph = cyclicGraph(letters);
        T letter = letters[0];
        Word<T> word = word(letter);
        graph.addEdgeFrom(word).to(word).label(letter);
        return graph;
    }

    @SuppressWarnings("unchecked")
    private static <T> Set<Edge<T>> loop(T letter) {
        Set<Edge<T>> set = new HashSet<Edge<T>>();
        set.add(new Edge<T>(word(letter), word(letter)));
        return set;
    }
}

class CycleWordCollector<T> implements EdgeYieldBlock<T> {

    private Word<T> word = empty();

    @Override
    public void yield(Edge<T> edge) {
        word = word.append(edge.label());
    }

    public Word<T> word() {
        return word;
    }

}
