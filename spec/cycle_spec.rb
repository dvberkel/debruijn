require 'cycle'
require 'graph'
require 'word'


describe "cycles" do
  it "should find a cycle over a graph" do
    cycle = Cycle.over(cyclicGraph("a", "b")).startingAt(Word.new("a"))
  end
end

def cyclicGraph(*alphabet) 
  graph = Graph.new()
  alphabet.each {|letter| graph.addVertex(Word.new(letter))}
  (0..alphabet.length).each { |index|
    source = Word.new(alphabet[index])
    sink = Word.new(alphabet[index + 1 % alphabet.length])
    graph.addEdgeFrom(source).to(sink)
  }
  graph
end
