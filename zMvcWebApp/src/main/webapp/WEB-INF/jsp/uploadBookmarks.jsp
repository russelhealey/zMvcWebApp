<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<c:url value="/uploadBookmarks/upload" var="uploadBookmarksUrl"/>

<html>
<head>
	<link rel='stylesheet' type='text/css' media='screen' href='<c:url value="./resources/css/style.css"/>'/>
	<script type='text/javascript' src='<c:url value="./resources/js/customUploadBookmarks.js"/>'></script>
	<script type='text/javascript' src='<c:url value="./resources/js/jquery.js"/>'></script>
	

	<title>User Records</title>
	
	<script type='text/javascript'>
	
	var uploadBookmarkUrlHolder = new Object();
	
	$(function() {
		// init
		
		uploadBookmarkUrlHolder.submit = '${uploadBookmarksUrl}';
		
		$('#uploadBookmarksBtn').click(function() { 
			uploadBookmarks();
		});


	});
	</script>
</head>

<body>
	<h1 id='banner'>Bookmark Upload</h1>
	<hr/>
	
	<div id='uploadForm'>
		<form>
			<textarea id="bookmarkXmlTxtArea" ></textarea>
  			
			<input id='uploadBookmarksBtn' type='submit' value='Submit'/>
		</form>
	</div>
	
</body>
</html>