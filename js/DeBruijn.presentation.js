(function($, _, Backbone, bruijn, undefined){
    bruijn.VariableView = Backbone.View.extend({
	initialize: function(){
	    this.render();
	},
	
	render: function(){
	    $(this.el).html("37");
	}
    });
})( jQuery, _, Backbone, DeBruijn );