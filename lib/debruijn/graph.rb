class Graph
  def initialize
    @vertices = []
    @edges = []
  end

  def add_vertex(vertex)
    @vertices << vertex
  end

  def all_vertices
    @vertices.each { |vertex|
      yield vertex
    }
  end

  def add_edge_from(source)
    EdgeAdder.new(self, source)
  end

  def add_edge(source, sink)
    edge = Edge.new(source, sink)
    @edges << edge
    edge
  end

  def all_adges
    @edges.each { |edge|
      yield edge
    }
  end

  def number_of_edges
    @edges.length
  end
end

class Edge
  attr_reader :source, :sink
  attr_accessor :label
  def initialize(source, sink)
    @source = source
    @sink = sink
    @label = nil
  end

  def ==(edge)
    self.eql?(edge)
  end
  
  def eql?(edge)
    self.class.eql?(edge.class) && self.source.eql?(edge.source) && self.sink.eql?(edge.sink)
  end
  
  def hash()
    @source.hash ^ @sink.hash
  end
end

class EdgeAdder
  def initialize(graph, source)
    @graph = graph
    @source = source
  end

  def to(sink)
    @graph.add_edge(@source, sink)
  end
end
