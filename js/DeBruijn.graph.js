(function($, _, Backbone, d3, bruijn, undefined){

    bruijn.GraphView = Backbone.View.extend({
	initialize: function(){
	    this.render();
	},
	
	render: function(){
	    var options = this.options;
	    var svg = d3.select("#" + $(this.el).attr('id')).append("svg")
	        .attr("width", options.width)
	        .attr("height", options.height);
	    
	    var nodes = [{word: "00"}, {word: "01"}, {word: "10"},  {word: "11"}];
	    var links = [
		{source: 0, target: 1, letter: "0"},
		{source: 0, target: 2, letter: "0"},
		{source: 0, target: 3, letter: "0"},
		{source: 2, target: 3, letter: "1"}
	    ];

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