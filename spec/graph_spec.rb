require "graph"
require "word"

describe "graph" do
  describe "vertices" do
    [
     ["a"], ["a", "b"], ["a", "b", "c"], ["a", "b", "c", "d"]
    ].each do |alphabet|
      it "should be able to add #{alphabet.length} vertices" do
        count, graph = 0, create_graph(alphabet)
        
        graph.all_vertices {count += 1}
        
        count.should == alphabet.length
      end

      it "should be able to add #{alphabet.length} correct vertices" do
        collector, graph = [], create_graph(alphabet)
        
        graph.all_vertices { |vertex| collector << vertex}
        
        collector.to_set.should == (alphabet.map {|letter| Word.new(letter)} ).to_set
      end
    end
  end

  describe "edges" do
    [
     [["a"], [["a", "a"]]],
     [["a", "b"], [["a", "b"], ["b", "a"]]],
     [["a", "b", "c"], [["a", "b"], ["b", "c"], ["c", "a"]]],
     [["a", "b", "c", "d"],[["a", "b"], ["b", "c"], ["c", "d"], ["d", "a"]]],
    ].each do |fixture|
      alphabet = fixture[0]
      edges = fixture[1]
      
      it "should be able to add #{edges.length} edges" do
        count, graph = 0, create_graph(alphabet, edges)

        graph.all_edges {count += 1}

        count.should == edges.length
      end
      
      it "should be able to add #{edges.length} correct edges" do
        collector, graph = [], create_graph(alphabet, edges)

        graph.all_edges { |edge| collector << edge}

        collector.to_set.should == (edges.map {|edge| Edge.new(Word.new(edge[0]), Word.new(edge[1]))}).to_set
      end
    end
  end
end

def create_graph(alphabet, edges=[])
  graph = Graph.new
  alphabet.each { |letter|
    graph.add_vertex(Word.new(letter))
  }
  edges.each { |edge|
    graph.add_edge_from(Word.new(edge[0])).to(Word.new(edge[1]))
  }
  graph
end
