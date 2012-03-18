class Cycle
  def self.over(graph)
    CycleBuilder.new(graph)
  end

  def initialize(edges, start)
    @cycle = []
    @edges = edges
    @start = start
    find_cycle_among_edges_from_start
  end

  def find_cycle_among_edges_from_start
  end
  
  def allEdges
    @cycle.each { |edge|
      yield edge
    }
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
