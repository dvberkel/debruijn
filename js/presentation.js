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
    });
})( jQuery );
