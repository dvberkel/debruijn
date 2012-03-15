class Words
  def self.over(*alphabet)
      Words.new(alphabet)
  end

  def initialize(*alphabet)
    @alphabet = alphabet.flatten
  end

  def of_length(n, &block)
    if (n == 0) then
      block.call(Word.new())
    else
      Words.over(@alphabet).of_length(n - 1) do |word|
        @alphabet.each { |letter|
          block.call(word.append(letter))
        }
      end
    end
  end
end
