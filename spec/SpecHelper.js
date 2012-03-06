beforeEach(function(){
    var arrayEquals = function(anArray, otherArray){
	if (anArray.length != otherArray.length) {
	    return false
	}
	for (var i = 0; i < anArray.length; i++) {
	    if (anArray[i] != otherArray[i]) {
		return false;
	    }
	}
	return true;
    }

    var does = function(array){
	return {
	    contain: function(target){
		for (var i = 0; i < array.length; i++) {
		    element = array[i];
		    if (element instanceof Array && target instanceof Array) {
			if (arrayEquals(element, target)) {
			    return true;
			}
		    }
		    if (element == target) {
			return true;
		    }
		}
		return false;
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