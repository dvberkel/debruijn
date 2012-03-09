(function($, _, Backbone, d3, bruijn, undefined){

    bruijn.GraphView = Backbone.View.extend({
	initialize: function(){
	    this.render();
	},
	
	render: function(){
	    $(this.el).html("Preparing graph");
	}
    });
})( jQuery, _, Backbone, d3, DeBruijn);