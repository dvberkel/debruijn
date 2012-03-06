beforeEach(function(){
    var does = function(array){
	return {
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
	    var actual = this.actual;
	    if (actual.length != expected.length) {
		return false;
	    }
	    for (var i = 0; i < expected.length; i++) {
		if (!does(actual).contain(expected[i])) {
		    return false;
		}
	    }
	    return true;
	}
    });
});