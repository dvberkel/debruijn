require "graph"
require "word"

describe "graph" do
  describe "vertices" do
    [
     ["a"], ["a", "b"], ["a", "b", "c"], ["a", "b", "c", "d"]
    ].each do |alphabet|
      it "should be able to add #{alphabet.length} vertices" do
        count, graph = 0, createGraph(alphabet)
        
        graph.allVertices {count += 1}
        
        count.should == alphabet.length
      end

      it "should be able to add #{alphabet.length} correct vertices" do
        collector, graph = [], createGraph(alphabet)
        
        graph.allVertices { |vertex| collector << vertex}
        
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
        count, graph = 0, createGraph(alphabet, edges)

        graph.allEdges {count += 1}

        count.should == edges.length
      end
    end
  end
end

def createGraph(alphabet, edges=[])
  graph = Graph.new
  alphabet.each { |letter|
    graph.addVertex(Word.new(letter))
  }
  edges.each { |edge|
    graph.addEdgeFrom(Word.new(edge[0])).to(Word.new(edge[1]))
  }
  graph
end
