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
	template: _.template("{ <%= alphabet %> }"),

	initialize: function(){
	    this.model.bind("change:k", function(){
		this.render();
	    }, this);
	    this.render();
	},
	
	render: function(){
	    $(this.el).html(this.template(this.alphabet()));
	},

	alphabet: function(){
	    var model = this.model;
	    var k = model.get("k");
	    var alphabet = model.get("alphabet");
	    if (k < 4) {
		return { alphabet: alphabet.slice(0, k).join(", ") };
	    } else {
		
		return { alphabet: [alphabet[0], alphabet[k - 1]].join(", ..., ")};
	    }
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

    bruijn.ProofView = Backbone.View.extend({
	initialize: function(){
	    this. render();
	},

	render: function(){
	    $(this.el).html("<tr><th>0</th><th>0</th><th>1</th><th>1</th><th>0</th></tr><tr><td>0</td><td>0</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
	}
    });
})( jQuery, _, Backbone, DeBruijn );