describe("expect(array).toContain(element)", function(){
    it("should correctly identify elements of a array", function(){
	expect(["a"]).toContain("a");
    });
});

describe("expect(array).toContainExactly(array)", function(){
    it("should accept same elements", function(){
	expect(["a", "b"]).toContainExactly(["a", "b"]);
    });

    it("should accept same elements even in different order", function(){
	expect(["a", "b"]).toContainExactly(["b", "a"]);
    });

    it("should not accept array of different length", function(){
	expect(["a", "b"]).not.toContainExactly(["a"]);
	expect(["a", "b"]).not.toContainExactly(["a", "b", "c"]);
    });

    it("should not accept array with different elements", function(){
	expect(["a", "b"]).not.toContainExactly(["a", "c"]);
    });
});