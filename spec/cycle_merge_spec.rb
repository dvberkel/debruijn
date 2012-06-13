require 'graph'
require 'word'
require 'cycle'

describe "cycle merge" do
  [
   [["a", "b", "c"], ["b", "d"], ["a", "b", "d", "b", "c"]],
   [["a", "b", "c"], ["c", "d"], ["a", "b", "c", "d", "c"]],
   [["a", "b", "c"], ["b", "d", "e"], ["a", "b", "d", "e", "b", "c"]],
   [["a", "b", "c"], ["c", "d", "e"], ["a", "b", "c", "d", "e", "c"]],
   [["a", "b", "c", "d"], ["b", "d", "e", "c", "f"], ["a", "b", "d", "e", "c", "f", "b", "c", "d"]],
  ].each do |fixture|
    a_alphabet = fixture[0]
    other_alphabet = fixture[1]
    expected = fixture[2]

    it "should correctly merge two cycles #{a_alphabet.join('->')} and #{other_alphabet.join('->')}" do
      a_cycle = Cycle.over(cyclic_graph(a_alphabet)).starting_at(Word.new(a_alphabet[0]))
      other_cycle = Cycle.over(cyclic_graph(other_alphabet)).starting_at(Word.new(other_alphabet[0]))
      
      cycle = a_cycle.merge(other_cycle)
      
      actual = Word.new()
      
      cycle.all_adges {|edge| actual = actual.append(edge.label)}
      
      actual.should == Word.new(expected)
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
