require "graph"
require "word"

describe "graph" do
  [
   ["a"],
   ["a", "b"],
   ["a", "b", "c"],
   ["a", "b", "c", "d"],
  ].each do |alphabet|
    it "should be able to add #{alphabet.length} vertices" do
      count, graph = 0, createGraph(alphabet)
      
      graph.allVertices {count += 1}
      
      count.should == alphabet.length
    end
  end
end

def createGraph(alphabet)
  graph = Graph.new
  alphabet.each { |letter|
    graph.addVertex(Word.new(letter))
  }
  graph
end
