(function($, _, Backbone, d3, bruijn, undefined){
    var on = function(word){
	return {
	    pipe: function(letter){
		result = word.slice(0);
		result.shift();
		result.push(letter);
		return result;
	    }
	};
    };

    var does = function(element) {
	if (element instanceof Array) {
	    return {
		equal: function(target) {
		    if (target instanceof Array) {
			if (element.length == target.length) {
			    for (var i = 0; i < element.length; i++) {
				if (!does(element[i]).equal(target[i])) {
				    return false;
				}
			    }
			    return true;
			}
		    }
		    return false;
		}
	    };
	} else {
	    return {
		equal: function(target){ return element == target }
	    };
	}
    };

    var within = function(array){
	return {
	    locate: function(target) {
		for (var i = 0; i < array.length; i++) {
		    var element = array[i];
		    if (does(element).equal(target)) {
			return i;
		    }
		}
		return -1;
	    }
	};
    };
    
    bruijn.GraphView = Backbone.View.extend({
	initialize: function(){
	    this.model.bind("change", function(){
		this.render();
	    }, this);
	    this.render();
	},
	
	render: function(){
	    var view = this;
	    var model = view.model;
	    var alphabet = model.get("alphabet").slice(0, model.get("k"));
	    var n = model.get("n");
	    
	    var combinations = [];
	    bruijn.Combinatorics.allCombinations(alphabet, n - 1, function(word){
		combinations.push(word);
	    });
	    
	    var nodes = _.map(combinations, function(combination){
		return {word: combination.join("")};
	    });
	    var links = [], searcher = within(combinations);
	    _.each(combinations, function(combination, sourceIndex){
		var combi = on(combination);
		_.each(alphabet, function(letter){
		    var target = combi.pipe(letter);
		    var targetIndex = searcher.locate(target);
		    links.push({source: sourceIndex, target: targetIndex, letter: letter});
		});
	    });

	    var options = view.options;
	    var element = $(view.el);
	    element.empty();
	    var svg = d3.select("#" + element.attr('id')).append("svg")
	        .attr("width", options.width)
	        .attr("height", options.height);

	    var force = d3.layout.force().charge(-120).linkDistance(30).size([options.width, options.height])
	        .nodes(nodes)
	        .links(links).start();
	    
	    var link = svg.selectAll("line.link")
		.data(links)
		.enter().append("line")
		.attr("class", "link")
		.style("stroke-width", 2);

	    var node = svg.selectAll("circle.node")
	        .data(nodes)
		.enter().append("circle")
		.attr("class", "node")
	        .attr("r", 5)
		.style("fill", "red")
		.call(force.drag);
	    
	    node.append("title").text(function(d){ return d.word });
	    
	    force.on("tick", function(){
		link.attr("x1", function(d){ return d.source.x })
		    .attr("y1", function(d){ return d.source.y })
		    .attr("x2", function(d){ return d.target.x })
		    .attr("y2", function(d){ return d.target.y });

		node.attr("cx", function(d){ return d.x })
		    .attr("cy", function(d){ return d.y });
	    });
	}
    });
})( jQuery, _, Backbone, d3, DeBruijn);