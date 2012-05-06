import unittest

from debruijn.graph.graph import Graph
from debruijn.combinatorics.word import Word

def createGraph(*alphabet):
    graph = Graph()
    
    for letter in alphabet:
        graph.addVertex(Word(letter))

    return graph
        

class testGraph(unittest.TestCase):
    def setUp(self):
        self.alphabet = ["a", "b", "c"]

    def testVertexCount(self):
        graph = createGraph(*(self.alphabet))
        count = 0

        for vertex in graph.allVertices():
            count = count + 1

        self.assertEquals(len(self.alphabet), count)
            
    def testVertices(self):
        graph = createGraph(*(self.alphabet))

        actual = [v for v in graph.allVertices()]

        for word in map((lambda v: Word(v)), self.alphabet):
            self.assertTrue(word in actual)
            

if __name__ == '__main__':
    unittest.main()
