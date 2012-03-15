require "word_generator"

describe "Words" do
  [ 
   ["a", "b"]
  ].each do |alphabet|
    [ 0, 1, 2, 3 ].each do |length|
      
      before(:each) do
        @words = Words.over(alphabet)
      end
      
      it "should generate all words over #{alphabet} of length #{length}" do
        count = 0;
        
        @words.of_length(length) {|word| count += 1}
        
        count.should == alphabet.length ** length
      end
    end
  end
end
