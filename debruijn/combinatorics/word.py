class Word:
    def __init__(self, *letters):
        self.letters = list(letters)
        
    def append(self, letter):
        letters = self.letters[:]
        letters.append(letter)
        return Word(*letters)

    def pipe(self, letter):
        letters = self.letters[1:]
        letters.append(letter)
        return Word(*letters)
    
    def __eq__(self, other):
        if (isinstance(other, Word)):
            return self.letters == other.letters
        return False

    def __hash__(self):
        return hash(self.letters)
    
    def __repr__(self):
        return "Word({0})".format(",".join(self.letters))
    
    def __str__(self):
        return "[ {0} ]".format(", ".join(self.letters))
