import unittest

from debruijn.combinatorics.word import Word
from debruijn.combinatorics.wordgenerator import allWordsOver

class testWordGenerator(unittest.TestCase):
    def testAllWordsOfLength0(self):
        alphabet = ["a", "b"]
        
        words = []
        for word in allWordsOver(*alphabet).ofLength(0):
            words.append(word)

        self.assertTrue(Word("") in words)

    def testAllWordsOfLength1(self):
        alphabet = ["a", "b"]
        
        words = []
        for word in allWordsOver(*alphabet).ofLength(1):
            words.append(word)

        self.assertTrue(Word("a") in words)
        self.assertTrue(Word("b") in words)

    def testAllWordsOfLength2(self):
        alphabet = ["a", "b"]
        
        words = []
        for word in allWordsOver(*alphabet).ofLength(2):
            words.append(word)

        self.assertTrue(Word("a", "a") in words)
        self.assertTrue(Word("a", "b") in words)
        self.assertTrue(Word("b", "a") in words)
        self.assertTrue(Word("b", "b") in words)

if __name__ == "__main__":
    unittest.main()
