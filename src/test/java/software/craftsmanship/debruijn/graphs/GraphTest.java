package software.craftsmanship.debruijn.graphs;

import static org.junit.Assert.assertEquals;
import static software.craftsmanship.debruijn.combinatorics.Word.word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import software.craftsmanship.debruijn.combinatorics.Word;
import software.craftsmanship.debruijn.graphs.block.EdgeCollectBlock;
import software.craftsmanship.debruijn.graphs.block.EdgeCountBlock;
import software.craftsmanship.debruijn.graphs.block.VertexCollectBlock;
import software.craftsmanship.debruijn.graphs.block.VertexCountBlock;

@RunWith(Parameterized.class)
public class GraphTest<T> {
    private final Set<Word<T>> vertices;
    private final Integer expectedVertexCount;
    private final Set<Edge<T>> edges;
    private final Integer expectedEdgeCount;
    private Graph<T> graph;

    public GraphTest(List<Word<T>> vertices, Set<Edge<T>> edges) {
        this.vertices = setOf(vertices);
        this.expectedVertexCount = vertices.size();
        this.edges = edges;
        this.expectedEdgeCount = edges.size();
    }

    private Set<Word<T>> setOf(List<Word<T>> vertices) {
        Set<Word<T>> vertexSet = new HashSet<Word<T>>();
        vertexSet.addAll(vertices);
        return vertexSet;
    }

    @Before
    public void createGraph() {
        this.graph = new Graph<T>();

        addVerticesTo(this.graph);
        addEdgesTo(this.graph);
    }

    private void addVerticesTo(Graph<T> aGraph) {
        for (Word<T> word : vertices) {
            aGraph.addVertex(word);
        }
    }

    private void addEdgesTo(Graph<T> aGraph) {
        for (Edge<T> edge : edges) {
            aGraph.addEdgeFrom(edge.source()).to(edge.sink());
        }
    }

    @Test
    public void shouldBeAbleToCountVertices() {
        VertexCountBlock<T> block = new VertexCountBlock<T>();

        graph.allVertices(block);

        assertEquals(expectedVertexCount, block.count());
    }

    @Test
    public void shouldBeAbleToCollectVertices() {
        VertexCollectBlock<T> block = new VertexCollectBlock<T>();

        graph.allVertices(block);

        assertEquals(vertices, block.vertices());
    }

    @Test
    public void shouldBeAbleToCountEdges() {
        EdgeCountBlock<T> block = new EdgeCountBlock<T>() {};

        graph.allEdges(block);

        assertEquals(expectedEdgeCount, block.count());
    }

    @Test
    public void shouldBeAbleToCollectEdges() {
        EdgeCollectBlock<T> block = new EdgeCollectBlock<T>() {};

        graph.allEdges(block);

        assertEquals(edges, block.edges());
    }

    @Parameters
    public static Collection<Object[]> data() {
        List<Object[]> data = new ArrayList<Object[]>();
        data.add(new Object[]{Arrays.asList(new Word[]{word("a")}),
                (new EdgeBuilder<String>()).from(word("a")).to(word("a")).build()});
        data.add(new Object[]{Arrays.asList(new Word[]{word("a"), word("b")}),
                (new EdgeBuilder<String>()).from(word("a")).to(word("a")).from(word("b")).to(word("a")).build()});
        data.add(new Object[]{Arrays.asList(new Word[]{word("a", "a")}),
                (new EdgeBuilder<String>()).from(word("a", "a")).to(word("a", "a")).build()});
        data.add(new Object[]{
                Arrays.asList(new Word[]{word("a", "a"), word("a", "b"), word("b", "a"), word("b", "b")}),
                (new EdgeBuilder<String>()).from(word("a", "a")).to(word("a", "b")).from(word("a", "b"))
                        .to(word("b", "a")).from(word("b", "a")).to(word("b", "b")).from(word("b", "b"))
                        .to(word("a", "a")).build()});
        return data;
    }
}

class EdgeBuilder<T> {
    private final Set<Edge<T>> edges = new HashSet<Edge<T>>();
    private Word<T> source;

    public EdgeBuilder<T> from(Word<T> source) {
        this.source = source;
        return this;
    }

    public EdgeBuilder<T> to(Word<T> sink) {
        edges.add(new Edge<T>(source, sink));
        return this;
    }

    public EdgeBuilder<T> andTo(Word<T> sink) {
        return to(sink);
    }

    public Set<Edge<T>> build() {
        return edges;
    }
}
