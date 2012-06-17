require "debruijn/version"
require "graph"
require "word_generator"
require "cycle"

module DeBruijn
  def self.over_alphabet(*alphabet)
     DeBruijnGenerator.new(alphabet) 
  end

  class DeBruijnGenerator
    def initialize(*alphabet)
      @alphabet = alphabet.flatten
    end

    def sequence(k, n)
      cycle = euler_cycle(de_bruijn_graph(k,n))
      
      word = Word.new()
      cycle.all_edges {|edge| word = word.append(edge.label)}
      word
    end

    def de_bruijn_graph(k,n)
      alphabet = @alphabet[0..(k-1)]
      graph = Graph.new
      Words.over(alphabet).of_length(n - 1) do |word|
        graph.add_vertex(word)
      end
      graph.all_vertices do |vertex|
        alphabet.each { |letter|
          graph.add_edge_from(vertex).to(vertex.pipe(letter)).label = letter
        }
      end
      graph
    end

    def euler_cycle(graph)
      cycle = EmptyCycle.new
      while (cycle.length < graph.number_of_edges)
        candidate, avoid = candidate_along(graph, cycle), []
        cycle.all_edges {|edge| avoid << edge}
        cycle = cycle.merge(Cycle.over(graph).avoiding(avoid).starting_at(candidate))
      end
      cycle
    end

    def candidate_along(graph, cycle)
      candidates = []
      if (cycle.length == 0)
        graph.all_edges {|edge| candidates << edge.source}
      else
        starting_points, avoid = Set.new, Set.new
        cycle.all_edges do |edge|
          starting_points << edge.source
          avoid << edge          
        end
        graph.all_edges do |edge|
          if (not avoid.include?(edge) && starting_points.include?(edge.source))
            candidates << edge.source
          end
        end
      end
      candidates[0]
    end
  end
end
