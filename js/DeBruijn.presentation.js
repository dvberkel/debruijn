(function($, _, Backbone, bruijn, undefined){
    bruijn.Model = Backbone.Model.extend({
	defaults: { k: 2, n: 3, alphabet: ["0", "1", "2", "3"] }
    });

    bruijn.VariableView = Backbone.View.extend({
	template: _.template("<span><%= value %></span><input type='text' value='<%= value %>' class='variable' size='1'/>"),

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
	    var control = function(){
		element.children().toggle();
		view.model.set(view.options.variable, parseInt(this.value));
	    };
	    element.children("input").blur(function(){
		    control.call(this);
	    }).keypress(function(e){
		var c = e.which ? e.which : e.keyCode;
		if (c == 13) {
		    control.call(this);
		}
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
	headerTemplate: _.template("<tr><th><%= header %></th></tr>"),
	rowTemplate: _.template("<tr><td><%= row %></td></tr>"),

	initialize: function(){
	    this.model.bind("change", function(){
		this.render();
	    }, this);
	    this. render();
	},

	render: function(){
	    var view = this;
	    var model = view.model;
	    var k = model.get("k");
	    var n = model.get("n");
	    var sequence = DeBruijn.sequence(model.get("alphabet").slice(0, k), n);
	    var element = $(view.el);
	    var header = element.children("thead");
	    var body = element.children("tbody").empty();
	    header.empty().append(view.headerTemplate({header: sequence.join("</th><th>")}));
	    var count = 0, blanks = view.blanks(sequence.length - n);
	    DeBruijn.Combinatorics.allCyclicSubsequences(sequence, n, function(subsequence){
		var row = view.roll(count++)(subsequence.concat(blanks));
		body.append(view.rowTemplate({row: row.join("</td><td>")}));
	    });
	},

	blanks: function(numberOfBlanks) {
	    var blanks = [];
	    for (var i = 0; i < numberOfBlanks; i++) {
		blanks.push("&nbsp;");
	    }
	    return blanks;
	},

	roll: function(i) {
	    return function(array) {
		var length = array.length;
		var cutAt = length - i;
		var result = array.slice(cutAt, cutAt + i).concat(array.slice(0, cutAt));
		return result;
	    }
	}
    });

    bruijn.CombinationsView = Backbone.View.extend({
	template : _.template("<li><%= word %></li>"),

	initialize : function(){
	    this.model.bind("change", function(){ this.render(); }, this);
	    this.render();
	},

	render : function(){
	    var view = this;
	    var model = this.model;
	    var alphabet = model.get("alphabet");
	    var k = model.get("k");
	    var n = model.get("n");
	    var element = $(view.el).empty();
	    var ul = $("<ul>").appendTo(element);
	    bruijn.Combinatorics.allCombinations(alphabet.slice(0,k), n-1, function(word){
		$(view.template({ word : word.join("") })).appendTo(ul);
	    });
	}
    });

    bruijn.PipeView = Backbone.View.extend({
	template : _.template("<li><%= word %> | <%= letter %> &rarr; <%= result %></li>"),

	initialize : function(){
	    this.model.bind("change", function(){ this.render(); }, this);
	    this.render();
	},

	render : function(){
	    var view = this;
	    var model = this.model;
	    var alphabet = model.get("alphabet").slice(0, model.get("k"));
	    var n = model.get("n");
	    var element = $(view.el).empty();
	    var ul = $("<ul>").appendTo(element);
	    bruijn.Combinatorics.allCombinations(alphabet, n-1, function(word){
		_.each(alphabet, function(letter){
		    var result = word.slice(1, word.length);
		    result.push(letter);
		    $(view.template({ 
			word : word.join(""), 
			letter: letter, 
			result: result.join("") 
		    })).appendTo(ul);
		});
	    });
	}
    });

    bruijn.EulerCycleView = Backbone.View.extend({
	initialize : function(){
	    this.render();
	},

	render : function(){
	    $(this.el).empty();
	    $(this.el).append("<h3>Euler Cycle</h3>");
	    $(this.el).append("<p>A path with same start as finish vertex which visits every edge.</p>");
	}
    });

    bruijn.EdgeLabelView = Backbone.View.extend({
	initialize : function(){
	    this.model.bind("change", function(){
		this.render();
	    }, this);
	    this.render();
	},

	render : function(){
	    var model = this.model;
	    var sequence = DeBruijn.sequence(model.get("alphabet").slice(0, model.get("k")), model.get("n"));
	    $(this.el).html(sequence.join(" "));
	}
    });
})( jQuery, _, Backbone, DeBruijn );