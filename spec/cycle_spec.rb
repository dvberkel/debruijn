require 'cycle'
require 'graph'
require 'word'


describe "cycles" do
  it "should find a cycle over a graph" do
    cycle = Cycle.over(cyclicGraph("a", "b")).startingAt(Word.new("a"))
    
    actual = Word.new()

    cycle.allEdges {|edge| actual = actual.append(edge.label)}

    actual.should == Word.new("a", "b")    
  end
end

def cyclicGraph(*alphabet) 
  graph = Graph.new()
  alphabet.each {|letter| graph.addVertex(Word.new(letter))}
  (0..alphabet.length - 1).each { |index|
    source = Word.new(alphabet[index])
    sink = Word.new(alphabet[index + 1 % alphabet.length])
    graph.addEdgeFrom(source).to(sink).label = alphabet[index]
  }
  graph
end
