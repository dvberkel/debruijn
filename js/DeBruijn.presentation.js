(function($, _, Backbone, bruijn, undefined){
    bruijn.Model = Backbone.Model.extend({
	defaults: { k: 2, n: 3, alphabet: ["0", "1", "2", "3"] }
    });

    bruijn.VariableView = Backbone.View.extend({
	template: _.template("<span><%= value %></span><input type='text' value='<%= value %>' size='1'/>"),

	initialize: function(){
	    this.model.bind("change:" + this.options.variable, function(){
		this.render();
	    }, this);
	    this.render();
	},
	
	render: function(){
	    var view = this;
	    var element = $(view.el);
	    element.html(view.template({
		value: view.model.get(view.options.variable)
	    }));
	    element.children("input").blur(function(){
		view.model.set(view.options.variable, parseInt(this.value));
	    }).hide();
	    element.children("span").click(function(){
		element.children().toggle();
		element.children("input").focus();
	    });
	}
    });

    bruijn.AlphabetView = Backbone.View.extend({
	initialize: function(){
	    this.render();
	},
	
	render: function(){
	    $(this.el).html("{0, 1}");
	}
    });

    bruijn.SequenceView = Backbone.View.extend({
	initialize: function(){
	    this.model.bind("change", function(){
		this.render();
	    }, this);
	    this.render()
	},
	
	render: function(){
	    var model = this.model;
	    var sequence = DeBruijn.sequence(model.get("alphabet").slice(0, model.get("k")), model.get("n"));
	    $(this.el).html(sequence.join(" "));
	}
    });
})( jQuery, _, Backbone, DeBruijn );