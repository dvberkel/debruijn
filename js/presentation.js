(function($, undefined){
    $(function(){
	$.deck(".slide");

	$("#toc").toc({
	    'selectors' : 'h2',
	    'container' : 'body',
	    'smoothScrolling': false,
	    'prefix': 'toc',
	    'highlightOnScroll': false
	});
	
	var example = new DeBruijn.Model();
	new DeBruijn.VariableView({el: $("#example-k"), model: example, variable: "k"});
	new DeBruijn.VariableView({el: $("#example-n"), model: example, variable: "n"});
	new DeBruijn.AlphabetView({el: $("#example-alphabet"), model: example});
	new DeBruijn.SequenceView({el: $("#example-sequence"), model: example});
	new DeBruijn.ProofView({el: $("#example-proof"), model: example});

	var graph = new DeBruijn.Model({alphabet: ["a", "b", "c", "d", "e"]});
	new DeBruijn.VariableView({el: $("#graph-k"), model: graph, variable: "k"});
	new DeBruijn.VariableView({el: $("#graph-n"), model: graph, variable: "n"});
	new DeBruijn.AlphabetView({el: $("#graph-alphabet"), model: graph});
	new DeBruijn.GraphView({el: $("#graph"), model: graph});
    });
})( jQuery );
