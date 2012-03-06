describe("DeBruijn", function(){
    it("should exist", function(){
	expect(DeBruijn).toBeDefined();
    });
    
    describe("DeBruijn.Combinatorics", function(){
	it("should exists", function(){
	    expect(DeBruijn.Combinatorics).toBeDefined();
	});

	describe("DeBruijn.Combinatorics.allCombinations", function(){
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

	    it("should yield all combinations of length 1", function(){
		DeBruijn.Combinatorics.allCombinations(["a", "b"], 1, callback);
		
		expect(collector).toContainExactly([["a"], ["b"]]);
	    });
	})
    });
});