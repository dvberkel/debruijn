require "word_generator"
require "set"

describe "Words" do
  [ 
   ["a", "b"]
  ].each do |alphabet|
    [ 0, 1, 2, 3 ].each do |length|
      
      before(:each) do
        @words = Words.over(alphabet)
      end
      
      it "should count all words over #{alphabet} of length #{length}" do
        count = 0;
        
        @words.of_length(length) {|word| count += 1}
        
        count.should == alphabet.length ** length
      end
    end
  end
  
  [
   [["a"], 0, [Word.new()].to_set],
   [["a"], 1, [Word.new("a")].to_set],
   [["a"], 2, [Word.new("a", "a")].to_set],
   [["a"], 3, [Word.new("a", "a", "a")].to_set],
   [["a", "b"], 0, [Word.new()].to_set],
   [["a", "b"], 1, [Word.new("a"), Word.new("b")].to_set],
   [["a", "b"], 2, [Word.new("a", "a"), Word.new("a", "b"), Word.new("b", "a"), Word.new("b", "b")].to_set],
  ].each do |fixture|
    alphabet = fixture[0]
    length = fixture[1]
    expected_set = fixture[2]
    it "should generate all the words over #{alphabet} of length #{length}" do
      words = []
      Words.over(alphabet).of_length(length) { |word|
        words << word
      }
      words.to_set.should == expected_set
    end
  end
end
