import unittest

from debruijn.graph.graph import Graph
from debruijn.graph.graph import Edge
from debruijn.combinatorics.word import Word

def createGraph(edges = [], *alphabet):
    graph = Graph()
    
    for letter in alphabet:
        graph.addVertex(Word(letter))

    for edge in edges:
        graph.addEdgeFrom(Word(edge[0])).to(Word(edge[1]))

    return graph
        

class testGraph(unittest.TestCase):
    def setUp(self):
        self.alphabet = ["a", "b", "c"]
        self.edges = [("a", "b"), ("b", "c"), ("c", "a")]
        self.graph = createGraph(self.edges, *self.alphabet)

    def testVertexCount(self):
        count = 0

        for vertex in self.graph.allVertices():
            count = count + 1

        self.assertEquals(len(self.alphabet), count)
            
    def testVertices(self):
        actual = [v for v in self.graph.allVertices()]

        for word in map((lambda v: Word(v)), self.alphabet):
            self.assertTrue(word in actual)

    def testEdgeCount(self):
        count = 0

        for edge in self.graph.allEdges():
            count = count + 1

        self.assertEquals(len(self.edges), count)

    def testEdgeEquality(self):
        self.assertTrue(Edge(Word("a"), Word("b")), Edge(Word("a"), Word("b")))

    def testEdges(self):
        actual = [e for e in self.graph.allEdges()]

        for edge in map((lambda e: Edge(Word(e[0]), Word(e[1]))), self.edges):
            self.assertTrue(edge in actual)
        

if __name__ == '__main__':
    unittest.main()
