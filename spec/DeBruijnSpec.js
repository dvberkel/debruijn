describe("DeBruijn", function(){
    it("should exist", function(){
	expect(DeBruijn).toBeDefined();
    });
    
    describe("DeBruijn.Combinatorics", function(){
	it("should exists", function(){
	    expect(DeBruijn.Combinatorics).toBeDefined();
	});

	describe("DeBruijn.Combinatorics.allCombinations()", function(){
	    var collector;
	    var callback;

	    beforeEach(function(){
		collector = [];
		callback = function(combination){
		    collector.push(combination);
		};
	    });

	    it("should exists", function(){
		expect(DeBruijn.Combinatorics.allCombinations).toBeDefined();
	    });

	    it("should yield all combinations of length 0", function(){
		DeBruijn.Combinatorics.allCombinations(["a", "b"], 0, callback);
		
		expect(collector).toContainExactly([[]]);
	    });

	    it("should yield all combinations of length 1", function(){
		DeBruijn.Combinatorics.allCombinations(["a", "b"], 1, callback);
		
		expect(collector).toContainExactly([["a"], ["b"]]);
	    });

	    it("should yield all combinations of length 2", function(){
		DeBruijn.Combinatorics.allCombinations(["a", "b"], 2, callback);
		
		expect(collector).toContainExactly([["a", "a"], ["a", "b"], ["b", "a"], ["b", "b"]]);
	    });
	});

	describe("DeBruijn.Combinatorics.allCyclicSubSequences()", function(){
	    var collector;
	    var callback;
	    
	    beforeEach(function(){
		collector = [];
		callback = function(subsequence){
		    collector.push(subsequence);
		};
	    });
	    
	    it("should exist", function(){
		expect(DeBruijn.Combinatorics.allCyclicSubsequences).toBeDefined();
	    });

	    it("should yield all subsequences of length 1", function(){
		DeBruijn.Combinatorics.allCyclicSubsequences(["a", "b", "c"], 1, callback);

		expect(collector).toContainExactly([["a"], ["b"], ["c"]]);
	    });

	    it("should yield all subsequences of length 2", function(){
		DeBruijn.Combinatorics.allCyclicSubsequences(["a", "b", "c"], 2, callback);

		expect(collector).toContainExactly([["a", "b"], ["b", "c"], ["c", "a"]]);
	    });
	});

	describe("DeBruijn.Combinatorics.lyndonWords", function(){
	    var collector;
	    var callback;

	    beforeEach(function(){
		collector = []
		callback = function(lyndonWord){
		    collector.push(lyndonWord);
		};
	    });

	    it("should exist", function(){
		expect(DeBruijn.Combinatorics.lyndonWords).toBeDefined();
	    });

	    it("should enumerate all Lyndon words upto length 1", function(){

		DeBruijn.Combinatorics.lyndonWords(2, 1, callback);
		
		expect(collector).toEqual([[0], [1]]);
	    });
	    
	    it("should enumerate all Lyndon words upto length 2", function(){

		DeBruijn.Combinatorics.lyndonWords(2, 2, callback);
		
		expect(collector).toEqual([[0], [0, 1] ,[1]]);
	    });

	    
	    it("should enumerate all Lyndon words upto length 3", function(){

		DeBruijn.Combinatorics.lyndonWords(2, 3, callback);
		
		expect(collector).toEqual([[0], [0, 0, 1], [0, 1], [0, 1, 1] ,[1]]);
	    });
	});
    });

    describe("DeBruijn.sequence",function(){
	it("should exist", function(){
	    expect(DeBruijn.sequence).toBeDefined();
	});

	it("should generate a valid De Bruijn sequence of length 1", function(){
	    expect(DeBruijn.sequence(["a", "b"], 1)).toBeDeBruijn(["a", "b"], 1);
	});

	it("should generate a valid De Bruijn sequence of length 2", function(){
	    expect(DeBruijn.sequence(["a", "b"], 2)).toBeDeBruijn(["a", "b"], 2);
	});

	it("should generate a valid De Bruijn sequence of length 3", function(){
	    expect(DeBruijn.sequence(["a", "b"], 3)).toBeDeBruijn(["a", "b"], 3);
	});
    });
});