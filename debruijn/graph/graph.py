class Graph:
    def __init__(self):
        self.vertices = []
        self.edges = []

    def addVertex(self, word):
        self.vertices.append(word)
    
    def allVertices(self):
        for word in self.vertices:
            yield word
    
    def addEdgeFrom(self, vertex):
        return EdgeAdder(self, vertex)
    
    def addEdge(self, edge):
        self.edges.append(edge)

    def allEdges(self):
        for edge in self.edges:
            yield edge

class EdgeAdder:
    def __init__(self, graph, vertex):
        self.graph = graph
        self.vertex = vertex

    def to(self, vertex):
        self.graph.addEdge(Edge(self.vertex, vertex))

class Edge:
    def __init__(self, source, sink):
        self.source = source
        self.sink = sink
        
    def __eq__(self, other):
        if (isinstance(other, Edge)):
            return self.source == other.source and self.sink == other.sink
        return false

    def __hash__(self):
        return hash(self.source) * hash(self.sink)

    def __repr__(self):
        return "Edge({0}, {1})".format(self.source, self.sink)

    def __str__(self):
        return "{{ {0} -> {1}  }}".format(self.source, self.sink)
