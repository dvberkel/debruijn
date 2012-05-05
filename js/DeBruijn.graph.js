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

    var allCombinations = function(alphabet, n) {
	var combinations = [];
	bruijn.Combinatorics.allCombinations(alphabet, n, function(word){
	    combinations.push(word);
	});
	return combinations;
    };

    var allNodes = function(combinations){
	return _.map(combinations, function(combination){
	    return {word: combination.join("")};
	});
    };

    var allLinks = function(alphabet, combinations){
	var links = [], searcher = within(combinations);
	_.each(combinations, function(combination, sourceIndex){
	    var combi = on(combination);
	    _.each(alphabet, function(letter){
		var target = combi.pipe(letter);
		var targetIndex = searcher.locate(target);
		links.push({
		    source: sourceIndex,
		    target: targetIndex, 
		    letter: letter, 
		    loop: sourceIndex == targetIndex
		});
	    });
	});
	return links;
    };

    bruijn.GraphView = Backbone.View.extend({
	initialize: function(){
	    this.model.bind("change", function(){
		this.render();
	    }, this);
	    this.render();
	},

	graphData: function(){
	    var view = this;
	    var model = view.model;
	    var alphabet = model.get("alphabet").slice(0, model.get("k"));
	    var n = model.get("n");
	    
	    var combinations = allCombinations(alphabet, n - 1);
	    
	    var nodes = allNodes(combinations);
	    var links = allLinks(alphabet, combinations);
	    
	    return {nodes: nodes, links: links};
	},
	
	render: function(){
	    var view = this;
	    var graphData = view.graphData();

	    var options = view.options;
	    var element = $(view.el);
	    element.empty();
	    var svg = d3.select("#" + element.attr('id')).append("svg")
	        .attr("viewBox", "0 0 400 400")
	        .attr("version", "1.1")
	        .attr("width", 400)
	        .attr("height", 400);
	    
	    var defs = svg.append("defs");
	    defs.append("marker")
	        .attr("id", "Arrow")
	        .attr("viewBox", "0 0 10 10")
	        .attr("refX", 15)
	        .attr("refY", 5)
	        .attr("markerUnits", "strokeWidth")
	        .attr("orient", "auto")
	        .attr("markerWidth", 6)
	        .attr("markerHeight", 4)
		    .append("polyline")
	            .attr("points", "0,0 10,5 0,10");
	    defs.append("marker")
	        .attr("id", "LoopArrow")
	        .attr("viewBox", "0 0 10 10")
	        .attr("refX", 0)
	        .attr("refY", 5)
	        .attr("markerUnits", "strokeWidth")
	        .attr("orient", "auto")
	        .attr("markerWidth", 6)
	        .attr("markerHeight", 4)
		    .append("polyline")
	            .attr("points", "0,0 10,5 0,10");
	    defs.append("marker")
	        .attr("id", "Loop")
	        .attr("viewBox", "0 0 12 12")
	        .attr("refX", 0)
	        .attr("refY", 6)
	        .attr("markerUnits", "strokeWidth")
	        .attr("orient", "auto")
	        .attr("stroke", "rgba(16,16,16, 0.5)")
	        .attr("fill", "none")
	        .attr("markerWidth", 10)
	        .attr("markerHeight", 10)
		    .append("path")
	            .attr("d", "M 0 6 q 9 -7 9 0 q 0 7 -9 0")
	            .attr("marker-mid", "url(#LoopArrow)")
	            .attr("stroke-width", 1);

	    var force = d3.layout.force().charge(-120).linkDistance(50).size([options.width, options.height])
	        .nodes(graphData.nodes)
	        .links(graphData.links).start();
	    
	    var link = svg.selectAll("line.link")
		.data(graphData.links)
		.enter().append("line")
		.attr("class", "link")
	        .attr("marker-end", function(d){
		    if (d.loop) {
			return "url(#Loop)";
		    } else {
			return "url(#Arrow)";
		    }
		})
	    
	    
	    var node = svg.selectAll("circle.node")
	        .data(graphData.nodes)
		.enter().append("circle")
		.attr("class", "node")
	        .attr("r", 5)
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