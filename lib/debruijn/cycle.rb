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
  end

  def merge(cycle)
    EmptyCycle.new
  end
  
  def all_edges
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

  def all_edges
  end
  
  def length
    0
  end
end

class CycleBuilder
  def initialize(graph)
    @edges = [].to_set
    graph.all_edges {|edge| @edges << edge}
  end

  def avoiding(*edges)
    @edges = @edges - edges.flatten
    self
  end

  def starting_at(vertex)
    Cycle.new(@edges, vertex)
  end
end
