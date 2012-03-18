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
    aAlphabet = fixture[0]
    otherAlphabet = fixture[1]
    expected = fixture[2]

    it "should correctly merge two cycles #{aAlphabet.join('->')} and #{otherAlphabet.join('->')}" do
      aCycle = Cycle.over(cyclicGraph(aAlphabet)).startingAt(Word.new(aAlphabet[0]))
      otherCycle = Cycle.over(cyclicGraph(otherAlphabet)).startingAt(Word.new(otherAlphabet[0]))
      
      cycle = aCycle.merge(otherCycle)
      
      actual = Word.new()
      
      cycle.allEdges {|edge| actual.append(edge.label)}
      
      actual.should == Word.new(expected)
    end
  end
end

def cyclicGraph(*alphabet)
  alphabet = alphabet.flatten
  graph = Graph.new()
  alphabet.each {|letter| graph.addVertex(Word.new(letter))}
  (0..alphabet.length - 1).each { |index|
    source = Word.new(alphabet[index])
    sink = Word.new(alphabet[(index + 1) % alphabet.length])
    graph.addEdgeFrom(source).to(sink).label = alphabet[index]
  }
  graph
end
