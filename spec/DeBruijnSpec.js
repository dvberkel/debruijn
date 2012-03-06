describe("DeBruijn", function(){
    it("should exist", function(){
	expect(DeBruijn).toBeDefined();
    });
    
    describe("DeBruijn.Combinatorics", function(){
	it("should exists", function(){
	    expect(DeBruijn.Combinatorics).toBeDefined();
	});

	describe("DeBruijn.Combinatorics.allCombinations", function(){
	    it("should exists", function(){
		expect(DeBruijn.Combinatorics.allCombinations).toBeDefined();
	    });

	    it("should yield all combinations over an alphabet of length 1", function(){
		var collector = [];
		
		DeBruijn.Combinatorics.allCombinations(["a", "b"], 1, function(combination){
		    collector.push(combination);
		});
		
		expect(collector).toEqual([["a"], ["b"]]);
	    });
	})
    });
});