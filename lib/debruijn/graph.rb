class Graph
  def initialize
    @vertices = []
  end

  def addVertex(vertex)
    @vertices << vertex
  end

  def allVertices
    @vertices.each { |vertex|
      yield vertex
    }
  end
end
