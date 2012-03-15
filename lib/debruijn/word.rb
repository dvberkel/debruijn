class Word
  attr_reader :letters
  def initialize(*letters)
    @letters = letters.flatten
  end

  def append(letter)
    return Word.new(Array.new(@letters) << letter)
  end

  def pipe(letter)
    result = Array.new(@letters).push(letter)
    result.shift
    Word.new(result)
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

  def to_s
    "(#{@letters.join(', ')})"
  end
end
