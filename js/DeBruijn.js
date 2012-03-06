(function(undefined){
    var Combinatorics = {
	allCombinations : function(alphabet, length, callback){
	    callback(["a"]);
	    callback(["b"])
	}
    };

    window.DeBruijn = {
	Combinatorics: Combinatorics
    };
})()