class Cycle
  def self.over(graph)
    CycleBuilder.new(graph)
  end

  attr_reader :start
  def initialize(edges, start, cycle = nil)
    if (cycle)
      @cycle = cycle
      @start = cycle[0].source
    else
      @cycle = []
      @edges = edges
      @start = start
      find_cycle_among_edges_from_start
    end
  end

  def find_cycle_among_edges_from_start
    path = find_a_path_among_edges_from_to(@edges, @start, @start)
    @cycle = path.reverse
  end
  
  def find_a_path_among_edges_from_to(available, origin, destination)
    available.each do |edge|
      if (edge.source == origin) 
        if (edge.sink == destination)
          return Array.new().push(edge)
        else
          copy = Set.new().union(available)
          copy.delete(edge)
          path = find_a_path_among_edges_from_to(copy, edge.sink, destination)
          if path
            return path << edge
          end
        end
      end
    end
  end

  def merge(cycle)
    EmptyCycle.new
  end
  
  def allEdges
    @cycle.each { |edge|
      yield edge
    }
  end

  def length
    @cycle.length
  end
end

class EmptyCycle
  def merge(cycle)
    cycle
  end

  def allEdges
  end
  
  def length
    0
  end
end

class CycleBuilder
  def initialize(graph)
    @edges = [].to_set
    graph.allEdges {|edge| @edges << edge}
  end

  def avoiding(*edges)
    @edges = @edges - edges.flatten
    self
  end

  def startingAt(vertex)
    Cycle.new(@edges, vertex)
  end
end
