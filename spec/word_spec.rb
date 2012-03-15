require "word"

describe "word" do
  it "should correctly append a letter" do
    word = Word.new("a")

    actual = word.append("b")

    actual.should == Word.new("a", "b")
  end
end
