describe("array[-index]", function(){
    it("is not possible to select an element with a negative index", function(){
	var array = ["a", "b", "c"];

	expect(array[-1]).not.toEqual("c");
    });
});