beforeEach(function(){
    var does = function(elements){
	return {
	    contain: function(element){
		for (var i = 0; i < elements.length; i++) {
		    if (elements[i] == element) {
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