describe("expect(array).toContain(element)", function(){
    it("should correctly identify elements of a array", function(){
	expect(["a"]).toContain("a");
    });
});

describe("expect(array).toContainExactly(array)", function(){
    var alphabet;

    beforeEach(function(){
	alphabet = ["a", "b"];
    });

    it("should accept same elements", function(){
	expect(alphabet).toContainExactly(["a", "b"]);
    });

    it("should accept same elements even in different order", function(){
	expect(alphabet).toContainExactly(["b", "a"]);
    });

    it("should not accept array of different length", function(){
	expect(alphabet).not.toContainExactly(["a"]);
	expect(alphabet).not.toContainExactly(["a", "b", "c"]);
    });

    it("should not accept array with different elements", function(){
	expect(alphabet).not.toContainExactly(["a", "c"]);
    });

    it("should accept array with array as elements", function(){
	expect([["a"], ["b"]]).toContainExactly([["a"], ["b"]]);
    });
});

describe("expect(array).toBeDeBruijn(alphabet, n)", function(){
    it("should identify De Bruijn sequence of length 1", function(){
	expect(["a", "b"]).toBeDeBruijn(["a", "b"], 1);
    });

    it("should identify De Bruijn sequence of length 2", function(){
	expect(["a", "a", "b", "b"]).toBeDeBruijn(["a", "b"], 2);
    });

    it("should identify De Bruijn sequence of length 2", function(){
	expect(["a", "a", "b", "b", "c", "c", "a", "c", "b"]).toBeDeBruijn(["a", "b", "c"], 2);
    });
});