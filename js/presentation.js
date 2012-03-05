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
    });
})( jQuery );
