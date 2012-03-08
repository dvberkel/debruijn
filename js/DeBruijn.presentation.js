(function($, _, Backbone, bruijn, undefined){
    bruijn.Model = Backbone.Model.extend({
	defaults: { k: 1, n: 3 }
    });

    bruijn.VariableView = Backbone.View.extend({
	initialize: function(){
	    this.render();
	},
	
	render: function(){
	    $(this.el).html(this.model.get("k"));
	}
    });
})( jQuery, _, Backbone, DeBruijn );