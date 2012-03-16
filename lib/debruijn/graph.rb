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
  def initialize(source, sink)
    @source = source
    @sink = sink
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
