require 'debruijn'
require 'word'

describe "debruijn" do
  [
   [Word.new("a","b"), 2, 1],
   [Word.new("a", "a", "b", "b"), 2, 2],
  ].each do |fixture|
    word = fixture[0]
    k = fixture[1]
    n = fixture[2]
    it "#{word} is a B(#{k}, #{n}) De Bruijn sequence" do
      word.should be_debruijn(k, n)
    end
  end
  
  [
   [2, 2],
   [3, 2],
   [3, 3]
  ].each do |fixture|
    k = fixture[0]
    n = fixture[1]
    it "should correctly create a B(#{k}, #{n}) De Bruijn sequence" do
      word = DeBruijn.overAlphabet("a", "b", "c").sequence(k, n)
      
      word.should be_debruijn(k,n)
    end
  end
end

RSpec::Matchers.define :be_debruijn do |k, n|
  match do |actual|
    words = Set.new
    actual.subwords(k) {|subword| words << subword}
    words.length == k**n
  end
end
