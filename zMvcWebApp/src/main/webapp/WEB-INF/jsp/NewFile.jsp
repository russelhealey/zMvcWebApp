<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<c:url value="/NewFile/update" var="editUrl"/>
<c:url value="/NewFile/records" var="recordsUrl"/>

<html>                                                                  
 <head>                                                                  
 <script type='text/javascript' src='<c:url value="./resources/js/jquery-1.6.4.min.js"/>'></script>
 <script type='text/javascript' src='<c:url value="./resources/js/custom.js"/>'></script>          
 <script type="text/javascript">                                         
 $(document).ready(function() {
	    $("input.flat").keypress( function (e) {
	        switch(e.keyCode){
	            // left arrow
	            case 37:
	                $(this).parent()
	                        .prev()
	                        .children("input.flat")
	                        .focus();
	                break;
	 
	            // right arrow
	            case 39:
	                $(this).parent()
	                        .next()
	                        .children("input.flat")
	                        .focus();
	                break;
	 
	            // up arrow
	            case 40:
	                $(this).parent()
	                        .parent()
	                        .next()
	                        .children("td")
	                        .children("input.flat[name="
	                            +$(this).attr("name")+"]")
	                        .focus();
	                break;
	 
	            // down arrow
	            case 38:
	                $(this).parent()
	                        .parent()
	                        .prev()
	                        .children("td")
	                        .children("input.flat[name="
	                            +$(this).attr("name")+"]")
	                        .focus();
	                break;
	        }
	    });
	});
 	
 </script>                                                               
 </head>                                                                 
 <body>       
 
 <table>
	<tr>
		<td><input class='flat' name='foo'></td>
		<td><input class='flat' name='bar'></td>
		<td><input class='flat' name='baz'></td>
		<td><input class='flat' name='bin'></td>
		<td><input class='flat' name='bom'></td>
		<td><input class='flat' name='bam'></td>
	</tr>
 
	<tr>
		<td><input class='flat' name='foo'></td>
		<td><input class='flat' name='bar'></td>
		<td><input class='flat' name='baz'></td>
		<td><input class='flat' name='bin'></td>
		<td><input class='flat' name='bom'></td>
		<td><input class='flat' name='bam'></td>
	</tr>
</table>
 	                                  
 </body>                                                                 
 </html>