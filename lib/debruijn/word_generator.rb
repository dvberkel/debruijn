class Words
  def self.over(*alphabet)
      Words.new(alphabet)
  end

  def initialize(*alphabet)
    @alphabet = alphabet.flatten
  end

  def of_length(n, &block)
    block.call(Word.new())
  end
end
