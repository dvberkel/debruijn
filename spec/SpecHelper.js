beforeEach(function(){
    var does = function(array){
	return {
	    containExactly : function(target){
		if (array.length != target.length) {
		    return false;
		}
		for (var i = 0; i < target.length; i++) {
		    if (!does(array).contain(target[i])) {
			return false;
		    }
		}
		return true;
	    },
	    contain: function(target){
		for (var i = 0; i < array.length; i++) {
		    element = array[i];
		    if (element instanceof Array && target instanceof Array) {
			if (does(element).equal(target)) {
			    return true;
			}
		    }
		    if (element == target) {
			return true;
		    }
		}
		return false;
	    },
	    equal: function(target){
		if (array.length != target.length) {
		    return false
		}
		for (var i = 0; i < array.length; i++) {
		    if (array[i] != target[i]) {
			return false;
		    }
		}
		return true;
	    }
	};
    };

    this.addMatchers({
	toContainExactly: function(expected){
	    return does(this.actual).containExactly(expected);
	},
	toBeDeBruijn: function(alphabet, order){
	    var subsequences = [];
	    DeBruijn.Combinatorics.allCyclicSubsequences(this.actual, order, function(subsequence){
		subsequences.push(subsequence);
	    });

	    var combinations = [];
	    DeBruijn.Combinatorics.allCombinations(alphabet, order, function(combination){
		combinations.push(combination);
	    });

	    return does(subsequences).containExactly(combinations);
	}
    });
});