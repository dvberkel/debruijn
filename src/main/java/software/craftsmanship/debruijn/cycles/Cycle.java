package software.craftsmanship.debruijn.cycles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import software.craftsmanship.debruijn.combinatorics.Word;
import software.craftsmanship.debruijn.graphs.Edge;
import software.craftsmanship.debruijn.graphs.Graph;
import software.craftsmanship.debruijn.graphs.block.EdgeCollectBlock;
import software.craftsmanship.debruijn.graphs.block.EdgeYieldBlock;

public class Cycle<T> {
    public static <U> CycleBuilder<U> cycleOver(Graph<U> graph) {
        EdgeCollectBlock<U> collector = new EdgeCollectBlock<U>();
        graph.allEdges(collector);
        return new CycleBuilder<U>(collector.edges());
    }

    private static <U> Set<Edge<U>> copyOf(Set<Edge<U>> aSet) {
        Set<Edge<U>> copy = new HashSet<Edge<U>>();
        copy.addAll(aSet);
        return copy;
    }

    private final List<Edge<T>> cycle = new ArrayList<Edge<T>>();
    private final Set<Edge<T>> edges = new HashSet<Edge<T>>();
    private Word<T> start;

    private Cycle(Set<Edge<T>> edges, Word<T> start) {
        this.edges.addAll(edges);
        this.start = start;
        findACycleAmongEdgesFromStart();
    }

    private Cycle(List<Edge<T>> edges) {
	this.cycle.addAll(edges);
	this.start = this.cycle.get(0).source();
    }
    
    protected Cycle() {
	// Constructor for the sole purpose of the EmptyCycle.
    }

    private void findACycleAmongEdgesFromStart() {
        List<Edge<T>> path = findAPathAmongEdges(start, start, copyOf(edges));
        if (path != null) {
            Collections.reverse(path);
            cycle.addAll(path);
        } else {
            throw new RuntimeException("No cycle found");
        }
     
    }

    private List<Edge<T>> findAPathAmongEdges(Word<T> origin, Word<T> destination, Set<Edge<T>> available) {
	for (Edge<T> edge : available) {
	    if (edge.source().equals(origin)) {
		if (edge.sink().equals(destination)) {
		    ArrayList<Edge<T>> path = new ArrayList<Edge<T>>();
		    path.add(edge);
		    return path;
		} else {
		    Set<Edge<T>> copy = copyOf(available);
		    copy.remove(edge);
		    List<Edge<T>> path = findAPathAmongEdges(edge.sink(), destination, copy);
		    if (path != null) {
			path.add(edge);
			return path;
		    }
		}
	    }
	}
	return null;
    }

    public void allEdges(EdgeYieldBlock<T> block) {
        for (Edge<T> edge : cycle) {
            block.yield(edge);
        }
    }
    
    public Cycle<T> merge(final Cycle<T> otherCycle) {
	CycleMergeCollectBlock<T> collector = new CycleMergeCollectBlock<T>(otherCycle);
	this.allEdges(collector);
	return new Cycle<T>(collector.edges);
    }
    
    public int size() {
	return cycle.size();
    }

    public static class CycleBuilder<T> {
        private final Set<Edge<T>> edges;

        public CycleBuilder(Set<Edge<T>> edgesOfGraph) {
            this.edges = copyOf(edgesOfGraph);
        }

        public CycleBuilder<T> avoiding(Cycle<T> cycle) {
            EdgeCollectBlock<T> collector = new EdgeCollectBlock<T>();
            cycle.allEdges(collector);
            return avoiding(collector.edges());

        }

        public CycleBuilder<T> avoiding(Set<Edge<T>> otherEdges) {
            edges.removeAll(otherEdges);
            return this;

        }

        public Cycle<T> startingAt(Word<T> start) {
            return new Cycle<T>(edges, start);
        }

    }
    class CycleMergeCollectBlock<U> implements EdgeYieldBlock<U> {
	final List<Edge<U>> edges = new ArrayList<Edge<U>>();
	private final Cycle<U> otherCycle;
	private boolean firstTime = true;
	
	public CycleMergeCollectBlock(Cycle<U> otherCycle) {
	    this.otherCycle = otherCycle;
	}
	
	@Override
	public void yield(Edge<U> edge) {
	    if (firstTime && edge.source().equals(otherCycle.start)) {
		firstTime = false;
		otherCycle.allEdges(new EdgeYieldBlock<U>(){
		    
		    @Override
		    public void yield(Edge<U> edge) {
			edges.add(edge);
		    }
		});
	    }
	    edges.add(edge);
	}
	
	public List<Edge<U>> edges() {
	    return edges;
	}
    }
}

