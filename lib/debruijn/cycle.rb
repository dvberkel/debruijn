class Cycle
  def self.over(graph)
    CycleBuilder.new(graph)
  end

  attr_reader :start
  def initialize(edges, start, cycle = nil)
    if (cycle)
      @cycles = cycle
      @start = cycle[0].source
    else
      @cycle = []
      @edges = edges
      @start = start
      find_cycle_among_edges_from_start
    end
  end

  def find_cycle_among_edges_from_start
  end

  def merge(cycle)
    EmptyCycle.new
  end
  
  def allEdges
    @cycle.each { |edge|
      yield edge
    }
  end
end

class EmptyCycle
  def allEdges
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
