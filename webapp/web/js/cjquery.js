  $(function() {
    $( "#dialog" ).dialog();
  });
  
  $(document).ready(function(){
    $("#flip").click(function(){
        $("#panel").slideToggle("slow");
  
    });
});
 $(document).ready(function(){
    $("#flip2").click(function(){
        $("#panel2").slideToggle("slow");
  
    });
});

 
 $(document).ready(function() {
     $(function() {
             $("#search").autocomplete({     
             source : function(request, response) {
             $.ajax({
                     url : "SearchController",
                     type : "GET",
                     data : {
                             term : request.term
                     },
                     dataType : "json",
                     success : function(data) {
                             response(data);
                     }
             });
     }
});
});
});
 
 
 $("#Delete").click(function(){
	   $("#profileform").attr("action", "ServiceMang");
	   $("#profileform").submit();
	})
	$("#Cycl").click(function(){
	   $("#profileform").attr("action", "Cyclic");
	   $("#profileform").submit();
	});
 $("#Delete2").click(function(){
	   $("#profileform2").attr("action", "ServiceMang");
	   $("#profileform2").submit();
	})
	$("#Cycl2").click(function(){
	   $("#profileform2").attr("action", "Cyclic");
	   $("#profileform2").submit();
	});
 
 

 
	

 
 
 
	
	