class Word
  attr_reader :letters
  def initialize(*letters)
    @letters = letters.flatten
  end

  def append(letter)
    result = Array.new(@letters)
    result << letter
    return Word.new(result)
  end

  def ==(word) 
    self.eql?(word)
  end

  def eql?(word)
    self.class.eql?(word.class) && self.letters.eql?(word.letters)    
  end

  def hash
    self.letters.hash
  end
end
