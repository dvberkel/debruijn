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
	new DeBruijn.VariableView({el: $("#example-k-a"), model: example, variable: "k"});
	new DeBruijn.VariableView({el: $("#example-n-a"), model: example, variable: "n"});
	new DeBruijn.AlphabetView({el: $("#example-alphabet-a"), model: example});
	new DeBruijn.VariableView({el: $("#example-k-b"), model: example, variable: "k"});
	new DeBruijn.VariableView({el: $("#example-n-b"), model: example, variable: "n"});
	new DeBruijn.AlphabetView({el: $("#example-alphabet-b"), model: example});
	new DeBruijn.SequenceView({el: $("#example-sequence"), model: example});
	new DeBruijn.ProofView({el: $("#example-proof"), model: example});

	var graph = new DeBruijn.Model({alphabet: ["a", "b", "c", "d", "e"]});
	new DeBruijn.VariableView({el: $("#graph-k"), model: graph, variable: "k"});
	new DeBruijn.VariableView({el: $("#graph-n"), model: graph, variable: "n"});
	new DeBruijn.AlphabetView({el: $("#graph-alphabet"), model: graph});
	new DeBruijn.GraphView({el: $("#graph"), model: graph, width: 400, height: 400});

	$("#overlay").addClass("overlay").hide();
	$("#combinations").mouseenter(function(e){
	    var overlay = $("#overlay").css({ left: e.pageX, top: e.pageY }).show();
	    new DeBruijn.CombinationsView({ el : overlay, model : graph });
	}).mouseleave(function(e){
	    $("#overlay").hide();
	});
	$("#pipe").mouseenter(function(e){
	    $("#overlay").css({ left: e.pageX, top: e.pageY }).show();
	}).mouseleave(function(e){
	    $("#overlay").hide();
	});
	$("#cycle").mouseenter(function(e){
	    $("#overlay").css({ left: e.pageX, top: e.pageY }).show();
	}).mouseleave(function(e){
	    $("#overlay").hide();
	});
    });
})( jQuery );
