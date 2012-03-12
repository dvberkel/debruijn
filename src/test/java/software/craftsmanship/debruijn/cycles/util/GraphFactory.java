package software.craftsmanship.debruijn.cycles.util;

import static software.craftsmanship.debruijn.combinatorics.Word.word;

import java.util.HashSet;
import java.util.Set;

import software.craftsmanship.debruijn.combinatorics.Word;
import software.craftsmanship.debruijn.graphs.Edge;
import software.craftsmanship.debruijn.graphs.Graph;

public class GraphFactory {

    public static <T> Graph<T> singletonGraph(T letter) {
        @SuppressWarnings("unchecked")
        Word<T> word = word(letter);
        Graph<T> graph = new Graph<T>();
        graph.addVertex(word);
        graph.addEdgeFrom(word).to(word).label(letter);
        return graph;
    }

    @SuppressWarnings("unchecked")
    public static <T> Graph<T> cyclicGraph(T... letters) {
        Graph<T> graph = new Graph<T>();
        for (T letter : letters) {
            Word<T> word = word(letter);
            graph.addVertex(word);
        }
        for (int index = 0; index < letters.length - 1; index++) {
            Word<T> source = word(letters[index]);
            Word<T> sink = word(letters[index + 1]);
            graph.addEdgeFrom(source).to(sink).label(letters[index]);
        }
        T lastLetter = letters[letters.length - 1];
        graph.addEdgeFrom(word(lastLetter)).to(word(letters[0])).label(lastLetter);
        return graph;
    }

    @SuppressWarnings("unchecked")
    public static <T> Graph<T> bulgeGraph(T... letters) {
        Graph<T> graph = cyclicGraph(letters);
        T letter = letters[0];
        Word<T> word = word(letter);
        graph.addEdgeFrom(word).to(word).label(letter);
        return graph;
    }

    @SuppressWarnings("unchecked")
    public static <T> Set<Edge<T>> loop(T letter) {
        Set<Edge<T>> set = new HashSet<Edge<T>>();
        set.add(new Edge<T>(word(letter), word(letter)));
        return set;
    }

}
