<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<c:url value="/bookmarkTree/records" var="recordsUrl"/>

<html>
<head>
	<link rel='stylesheet' type='text/css' media='screen' href='<c:url value="./resources/css/style.css"/>'/>
	<script type='text/javascript' src='<c:url value="./resources/js/jquery-1.6.4.min.js"/>'></script>
	<script type='text/javascript' src='<c:url value="./resources/js/jquery.jstree.js"/>'></script>
	<script type='text/javascript' src='<c:url value="./resources/js/jquery.hotkeys.js"/>'></script>
	<!--  <script type='text/javascript' src='<c:url value="./resources/js/customBookmarksTree.js"/>'></script>-->

	<title>User Records</title>
	
	<script type='text/javascript'>
	var urlHolder = new Object();
	$(function () {
		
		urlHolder.records = '${recordsUrl}';
		loadBookmarkTreeTable();
		
	});
	
	function open_win(url){
		window.open(url);
	}

	function loadBookmarkTreeTable() {
		$.ajax({
			type: "get",
			  url: urlHolder.records,
			  async: false,
			  success: function ( response, textStatus, jqXHR ) {
				  var previousFolder = '';
		 			var row = '';
			 		for (var i=0; i<response.bookmarks.length; i++) {
			 			var folder = response.bookmarks[i].folder;
						if(i == 0){
							row += '<ul><li id="folder' + i + '"><a href="#">' + folder + '</a><ul>';
						}else if(i != 0 && previousFolder != folder){
							row += '</ul></li>';
							row += '<li id="folder' + i + '"><a href="#">' + folder + '</a><ul>';
						}
						row += '<li id="urlItem' + i + '"><input style="background-color: #FFFFFF;" type="button" value="' + response.bookmarks[i].url + '" onclick="open_win( \'' + response.bookmarks[i].url + '\')" /></li>';
				 		if(response.bookmarks.length == (i + 1)){
				 			row += '</ul></li></ul>';
				 		}
				 		previousFolder = folder;
			 		}
			 		$('#bookmarksDiv').append(row);
			 		$("#bookmarksDiv")
					// call `.jstree` with the options object
					.jstree({
						
					})
					// EVENTS
					// each instance triggers its own events - to process those listen on the container
					// all events are in the `.jstree` namespace
					// so listen for `function_name`.`jstree` - you can function names from the docs
					.bind("loaded.jstree", function (event, data) {
						// you get two params - event & data - check the core docs for a detailed description
					});
			  },
			  error: function (jqXHR, textStatus, errorThrown) {
				  alert('error' + ' ' + textStatus + ' ' +  errorThrown);
			  }
		});
	}

	</script>
</head>

<body>
	<div id="bookmarksDiv"  style="height:100px;">
		
	</div>
	
	<form action='./bookmarks/download' method='get'>
		<input type='submit' value='Download'></input>
	</form>
	
	<form  enctype="multipart/form-data" action="./bookmarks/upload" method="post">
	  		<table border='0' >
            <tr>
            	<td>
            		<b>Choose the file To Upload:</b>
           		</td>
            	<td>
            		<input name="F1" type="file" value="theFile"></input>
            	</td>
            </tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="Send File" ></input>
				</td>
			</tr>
             </table>
     </form>
	
</body>
</html>