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
    @edges << Edge.new(source, sink)
  end

  def allEdges
    @edges.each { |edge|
      yield edge
    }
  end
end

class Edge
  attr_reader :source, :sink
  def initialize(source, sink)
    @source = source
    @sink = sink
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
    self
  end
end
