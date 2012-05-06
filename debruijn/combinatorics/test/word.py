import unittest

from debruijn.combinatorics.word import Word

class testWord(unittest.TestCase):
    def testAppend(self):
        self.assertEquals(Word("a"), Word().append("a"))
        self.assertEquals(Word("a", "b"), Word("a").append("b"))
        self.assertEquals(Word("a", "b", "c"), Word("a", "b").append("c"))

if __name__ == '__main__':
    unittest.main()
