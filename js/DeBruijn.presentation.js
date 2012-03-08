(function($, _, Backbone, bruijn, undefined){
    bruijn.Model = Backbone.Model.extend({
	defaults: { k: 2, n: 3 }
    });

    bruijn.VariableView = Backbone.View.extend({
	template: _.template("<span><%= value %></span><input type='text' value='<%= value %>' size='1'/>"),
	show: true,

	initialize: function(){
	    this.render();
	},
	
	render: function(){
	    $(this.el).html(this.template({
		value: this.model.get(this.options.variable)
	    }));
	    var elementToHide;
	    if (this.show) {
		elementToHide = $(this.el).children("input");
	    } else {
		elementToHide = $(this.el).children("span");
	    }
	    elementToHide.hide();
	}
    });
})( jQuery, _, Backbone, DeBruijn );