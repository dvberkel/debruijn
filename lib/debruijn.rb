require "debruijn/version"
require "graph"
require "word_generator"
require "cycle"

module DeBruijn
  def self.overAlphabet(*alphabet)
     DeBruijnGenerator.new(alphabet) 
  end

  class DeBruijnGenerator
    def initialize(*alphabet)
      @alphabet = alphabet.flatten
    end

    def sequence(k, n)
      cycle = eulerCycle(de_bruijn_graph(k,n))
      
      word = Word.new()
      cycle.allEdges {|edge| word = word.append(edge.label)}
      word
    end

    def de_bruijn_graph(k,n)
      alphabet = @alphabet[0..(k-1)]
      graph = Graph.new
      Words.over(alphabet).of_length(n - 1) do |word|
        graph.addVertex(word)
      end
      graph.allVertices do |vertex|
        alphabet.each { |letter|
          graph.addEdgeFrom(vertex).to(vertex.pipe(letter)).label = letter
        }
      end
      graph
    end

    def eulerCycle(graph)
      cycle = EmptyCycle.new
      while (cycle.length < graph.numberOfEdges)
        candidate, avoid = candidateAlong(graph, cycle), []
        cycle.allEdges {|edge| avoid << edge}
        cycle = cycle.merge(Cycle.over(graph).avoiding(avoid).startingAt(candidate))
      end
      cycle
    end

    def candidateAlong(graph, cycle)
      candidates = []
      if (cycle.length == 0)
        graph.allEdges {|edge| candidates << edge.source}
      else
        startingPoints, avoid = Set.new, Set.new
        cycle.allEdges do |edge|
          startingPoints << edge.source
          avoid << edge          
        end
        graph.allEdges do |edge|
          if (not avoid.include?(edge) && startingPoints.include?(edge.source))
            candidates << edge.source
          end
        end
      end
      candidates[0]
    end
  end
end
