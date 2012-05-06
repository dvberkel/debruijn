class Graph:
    def __init__(self):
        self.vertices = []

    def addVertex(self, word):
        self.vertices.append(word)
    
    def allVertices(self):
        for word in self.vertices:
            yield word
