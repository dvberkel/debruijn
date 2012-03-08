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

	new DeBruijn.VariableView({el: $("#example-k")});
    });
})( jQuery );
