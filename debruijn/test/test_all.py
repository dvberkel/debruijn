import unittest

from debruijn.combinatorics.test.word import testWord
from debruijn.combinatorics.test.wordgenerator import testWordGenerator

class EvaluateSuite(unittest.TestSuite):
    def __init__(self):
        unittest.TestSuite.__init__(self)
        for clazz in [testWord, testWordGenerator]:
            self.addTest(unittest.makeSuite(clazz))

if __name__ == '__main__':
    suite = EvaluateSuite()

    unittest.TextTestRunner().run(suite)
