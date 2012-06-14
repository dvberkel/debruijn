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
      cycle = Cycle.over(cyclic_graph(alphabet)).starting_at(Word.new(alphabet[0]))
      
      actual = Word.new()
      
      cycle.all_adges {|edge| actual = actual.append(edge.label)}
      
      actual.should == Word.new(alphabet)    
    end

    it "should find a cycle over in a cyclic graph over [#{alphabet.join(', ')}]" do
      graph = cyclic_graph(alphabet)
      letter = alphabet[0]
      graph.add_edge_from(Word.new(letter)).to(Word.new(letter))
     
      cycle = Cycle.over(graph).avoiding(Edge.new(letter, letter)).starting_at(Word.new(letter))
      
      actual = Word.new()
      
      cycle.all_adges {|edge| actual = actual.append(edge.label)}
      
      actual.should == Word.new(alphabet)    
    end
  end
end

def cyclic_graph(*alphabet)
  alphabet = alphabet.flatten
  graph = Graph.new()
  alphabet.each {|letter| graph.add_vertex(Word.new(letter))}
  (0..alphabet.length - 1).each { |index|
    source = Word.new(alphabet[index])
    sink = Word.new(alphabet[(index + 1) % alphabet.length])
    graph.add_edge_from(source).to(sink).label = alphabet[index]
  }
  graph
end
