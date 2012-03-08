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
	    while (word.length > 0) {
		word[word.length - 1] += 1;
		callback(word.slice(0));
		var initialLength = word.length;
		while (word.length < n) {
		    word.push(word[word.length - initialLength]);
		}
		while (word.length > 0 && word[word.length - 1] == k - 1) {
		    word.pop();
		}
	    }
	}
    };

    var map = function(array, f){
	var result = [];
	for (var i = 0; i < array.length; i++) {
	    result.push(f(array[i]));
	}
	return result;	
    };

    var sequence = function(alphabet, n) {
	var sequence = [], mapping = function(index){
	    return alphabet[index];
	};
	DeBruijn.Combinatorics.lyndonWords(alphabet.length, n, function(word){
	    if (n % word.length == 0) {
		sequence = sequence.concat(map(word, mapping));
	    }
	});
	return sequence;
    };

    window.DeBruijn = {
	Combinatorics: Combinatorics,
	sequence: sequence
    };
})()