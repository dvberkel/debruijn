require "word"

describe "word" do
  [
   [Word.new(), "a", Word.new("a")], 
   [Word.new(), "b", Word.new("b")], 
   [Word.new("a"), "a", Word.new("a", "a")], 
   [Word.new("a"), "b", Word.new("a", "b")],
   [Word.new("b"), "a", Word.new("b", "a")], 
   [Word.new("b"), "b", Word.new("b", "b")],
   [Word.new("a", "a"), "a", Word.new("a", "a", "a")],
   [Word.new("a", "a"), "b", Word.new("a", "a", "b")],
   [Word.new("a", "b"), "a", Word.new("a", "b", "a")],
   [Word.new("a", "b"), "b", Word.new("a", "b", "b")],
   [Word.new("b", "a"), "a", Word.new("b", "a", "a")],
   [Word.new("b", "a"), "b", Word.new("b", "a", "b")],
   [Word.new("b", "b"), "a", Word.new("b", "b", "a")],
   [Word.new("b", "b"), "b", Word.new("b", "b", "b")],
  ].each do |triple|
    word, letter, expected = triple
    it "#{word}.append(#{letter}) should == #{expected}" do      
      actual = word.append(letter)
      
      actual.should == expected
    end
  end

  [
   [Word.new(), "a", Word.new()], 
   [Word.new(), "b", Word.new()], 
   [Word.new("a"), "a", Word.new("a")], 
   [Word.new("a"), "b", Word.new("b")],
   [Word.new("b"), "a", Word.new("a")], 
   [Word.new("b"), "b", Word.new("b")],
   [Word.new("a", "a"), "a", Word.new("a", "a")],
   [Word.new("a", "a"), "b", Word.new("a", "b")],
   [Word.new("a", "b"), "a", Word.new("b", "a")],
   [Word.new("a", "b"), "b", Word.new("b", "b")],
   [Word.new("b", "a"), "a", Word.new("a", "a")],
   [Word.new("b", "a"), "b", Word.new("a", "b")],
   [Word.new("b", "b"), "a", Word.new("b", "a")],
   [Word.new("b", "b"), "b", Word.new("b", "b")],
  ].each do |triple|
    word, letter, expected = triple
    it "#{word}.pipe(#{letter}) should == #{expected}" do      
      actual = word.pipe(letter)
      
      actual.should == expected
    end
  end
end
