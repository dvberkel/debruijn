class Graph
  def initialize
    @vertices = []
    @edges = []
  end

  def addVertex(vertex)
    @vertices << vertex
  end

  def allVertices
    @vertices.each { |vertex|
      yield vertex
    }
  end

  def addEdgeFrom(source)
    EdgeAdder.new(self, source)
  end

  def addEdge(source, sink)
    edge = Edge.new(source, sink)
    @edges << edge
    edge
  end

  def allEdges
    @edges.each { |edge|
      yield edge
    }
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
    @graph.addEdge(@source, sink)
  end
end
