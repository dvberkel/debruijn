package software.craftsmanship.debruijn;

import static software.craftsmanship.debruijn.combinatorics.WordGenerator.allWordsOver;
import static software.craftsmanship.debruijn.cycles.Cycle.cycleOver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import software.craftsmanship.debruijn.combinatorics.Word;
import software.craftsmanship.debruijn.combinatorics.block.WordYieldBlock;
import software.craftsmanship.debruijn.cycles.Cycle;
import software.craftsmanship.debruijn.cycles.EmptyCycle;
import software.craftsmanship.debruijn.graphs.Edge;
import software.craftsmanship.debruijn.graphs.Graph;
import software.craftsmanship.debruijn.graphs.block.EdgeYieldBlock;
import software.craftsmanship.debruijn.graphs.block.LabelCollector;
import software.craftsmanship.debruijn.graphs.block.VertexYieldBlock;

public class DeBruijn<T> {

    public static <U> DeBruijn<U> overAlphabet(U... alphabet) {
	return new DeBruijn<U>(Arrays.asList(alphabet));
    }

    private final List<T> alphabet;
    
    private DeBruijn(List<T> alphabet) {
	this.alphabet = alphabet;
    }

    public Word<T> sequence(int k, int n) {
	Graph<T> graph = deBruijnGraph(k, n - 1);
	Cycle<T> cycle = eulerCycle(graph);
	LabelCollector<T> collector = new LabelCollector<T>();
	cycle.allEdges(collector);
	return collector.word();
    }

    private Graph<T> deBruijnGraph(int k, int m) {
	Graph<T> graph = new Graph<T>();
	List<T> currentAlphabet = alphabet.subList(0, k);
	addVertices(m, graph, currentAlphabet);
	addEdged(graph, currentAlphabet);
	return graph;
    }

    @SuppressWarnings("unchecked")
    private void addVertices(int m, final Graph<T> graph, final List<T> currentAlphabet) {
	allWordsOver((T[])currentAlphabet.toArray()).ofLength(m).each(new WordYieldBlock<T>() {
	    @Override
	    public void yield(Word<T> word) {
		graph.addVertex(word);
	    }
	});
    }

    private void addEdged(final Graph<T> graph, final List<T> currentAlphabet) {
	graph.allVertices(new VertexYieldBlock<T>() {
	    @Override
	    public void yield(Word<T> word) {
		for (T letter : currentAlphabet) {
		    graph.addEdgeFrom(word).to(word.pipe(letter)).label(letter);
		}
	    }
	});
    }
    
    private Cycle<T> eulerCycle(Graph<T> graph) {
	Cycle<T> cycle = new EmptyCycle<T>();
	do {
	    Word<T> candidate = candidateAlong(graph, cycle);
	    cycle = cycle.merge(cycleOver(graph).avoiding(cycle).startingAt(candidate));
	} while (cycle.size() != graph.numberOfEdges());
	return cycle;
    }
    
    private Word<T> candidateAlong(Graph<T> graph, Cycle<T> cycle) {
	final List<Word<T>> candidates = new ArrayList<Word<T>>();
	if (cycle.size() == 0) {
	    graph.allVertices(new VertexYieldBlock<T>() {
		@Override
		public void yield(Word<T> word) {
		    candidates.add(word);
		}
	    });
	} else {
	    final Set<Word<T>> startingPoints = new HashSet<Word<T>>();
	    final Set<Edge<T>> avoid = new HashSet<Edge<T>>();
	    cycle.allEdges(new EdgeYieldBlock<T>() {
    	    	@Override
    	    	public void yield(Edge<T> edge) {
    	    	    startingPoints.add(edge.source());
    	    	    avoid.add(edge);
    	    	}
    	    });
    	    graph.allEdges(new EdgeYieldBlock<T>() {
    		@Override
    		public void yield(Edge<T> edge) {
    		    if (!avoid.contains(edge) && startingPoints.contains(edge.source())) {
    			candidates.add(edge.source());
    		    }
    		}
    	    });
	}
	return candidates.get(0);
    }

}
