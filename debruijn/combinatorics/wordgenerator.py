from debruijn.combinatorics.word import Word

def allWordsOver(*alphabet):
    return WordGenerator(list(alphabet))

class WordGenerator:
    def __init__(self, alphabet):
        self.alphabet = alphabet
    
    def ofLength(self, n):
        yield Word()
