class Cycle
  def self.over(graph)
    CycleBuilder.new(graph)
  end

  def initialize(edges, start)
    @edges = edges
    @start = start
  end
end

class CycleBuilder
  def initialize(graph)
    @edges = [].to_set
    graph.allEdges {|edge| @edges << edge}
  end

  def startingAt(vertex)
    Cycle.new(@edges, vertex)
  end
end
