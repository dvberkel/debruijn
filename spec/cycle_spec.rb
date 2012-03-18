require 'cycle'
require 'graph'
require 'word'


describe "cycles" do
  [
   ["a", "b"],
   ["a", "b", "c"],
   ["a", "b", "c", "d"],
   ["a", "b", "c", "d", "e"],
  ].each do |alphabet|
    it "should find a cycle over in a cyclic graph over [#{alphabet.join(', ')}]" do
      cycle = Cycle.over(cyclicGraph(alphabet)).startingAt(Word.new(alphabet[0]))
      
      actual = Word.new()
      
      cycle.allEdges {|edge| actual = actual.append(edge.label)}
      
      actual.should == Word.new(alphabet)    
    end
  end
end

def cyclicGraph(*alphabet)
  alphabet = alphabet.flatten
  graph = Graph.new()
  alphabet.each {|letter| graph.addVertex(Word.new(letter))}
  (0..alphabet.length - 1).each { |index|
    source = Word.new(alphabet[index])
    sink = Word.new(alphabet[index + 1 % alphabet.length])
    graph.addEdgeFrom(source).to(sink).label = alphabet[index]
  }
  graph
end
