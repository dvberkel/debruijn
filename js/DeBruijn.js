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
	},
	allCyclicSubsequences : function(sequence, length, callback) {
	    doubleSequence = sequence.concat(sequence);
	    for (var i = 0; i < sequence.length; i++) {
		callback(doubleSequence.slice(i, i+length));
	    }
	},
	lyndonWords : function(k, n, callback) {
	    var word = [-1];
	    while (false) {
		word[word.length - 1] += 1;
		callback(word);
		var length = m.length;
		
	    }
	    callback([0]);
	    callback([1]);
	}
    };

    window.DeBruijn = {
	Combinatorics: Combinatorics
    };
})()