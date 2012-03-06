(function(undefined){
    var Combinatorics = {
	allCombinations : function(alphabet, length, callback){
	    if (length <= 0) {
		callback([]);
	    } else {
		Combinatorics.allCombinations(alphabet, length - 1, function(combination){
		    for (var i = 0; i < alphabet.length; i++) {
			var copy = combination.slice(0);
			copy.push(alphabet[i]);
			callback(copy);
		    }
		});
	    }
	}
    };

    window.DeBruijn = {
	Combinatorics: Combinatorics
    };
})()