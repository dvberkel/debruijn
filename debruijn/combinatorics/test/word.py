import unittest

from debruijn.combinatorics.word import Word

class testWord(unittest.TestCase):
    def testAppend(self):
        self.assertEquals(Word("a"), Word().append("a"))
        self.assertEquals(Word("a", "b"), Word("a").append("b"))
        self.assertEquals(Word("a", "b", "c"), Word("a", "b").append("c"))

    def testPipe(self):
        self.assertEquals(Word("b"), Word("a").pipe("b"))
        self.assertEquals(Word("b", "a"), Word("a", "b").pipe("a"))
        self.assertEquals(Word("b", "c", "d"), Word("a", "b", "c").pipe("d"))

if __name__ == '__main__':
    unittest.main()
