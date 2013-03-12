<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<c:url value="/bookmarks/records" var="recordsUrl"/>
<c:url value="/bookmarks/create" var="addUrl"/>
<c:url value="/bookmarks/update" var="editUrl"/>
<c:url value="/bookmarks/delete" var="deleteUrl"/>

<html>
<head>
	<link rel='stylesheet' type='text/css' media='screen' href='<c:url value="./resources/css/style.css"/>'/>
	<script type='text/javascript' src='<c:url value="./resources/js/customBookmarks.js"/>'></script>
	<script type='text/javascript' src='<c:url value="./resources/js/jquery.js"/>'></script>
	

	<title>User Records</title>
	
	<script type='text/javascript'>
	
	var bookmarkUrlHolder = new Object();
	
	$(function() {
		// init
		
		bookmarkUrlHolder.records = '${recordsUrl}';
		bookmarkUrlHolder.add = '${addUrl}';
		bookmarkUrlHolder.edit = '${editUrl}';
		bookmarkUrlHolder.del = '${deleteUrl}';

		loadBookmarkTable();
		
		$('#newBtn').click(function() { 
			toggleBookmarkForms('new');
			toggleCrudBookmarkButtons('hide');
		});
		
		$('#editBtn').click(function() { 
			if (hasBookmarkSelected()) {
				toggleBookmarkForms('edit');
				toggleCrudBookmarkButtons('hide');
				fillEditBookmarkForm();
			}
		});
		
		$('#reloadBtn').click(function() { 
			loadBookmarkTable();
		});

		$('#deleteBtn').click(function() {
			if (hasBookmarkSelected()) { 
				submitDeleteBookmarkRecord();
			}
		});
		
		$('#newForm').submit(function() {
			
			submitNewBookmarkRecord();
		});
		
		$('#editForm').submit(function() {
			
			submitUpdateBookmarkRecord();
		});

		$('#closeNewForm').click(function() { 
			toggleBookmarkForms('hide'); 
			toggleCrudBookmarkButtons('show');
		});
		
		$('#closeEditForm').click(function() { 
			toggleBookmarkForms('hide'); 
			toggleCrudBookmarkButtons('show');
		});
	});
	</script>
</head>

<body>
	<h1 id='banner'>Bookmark</h1>
	<hr/>
	
	<div id='controlBar'>
		<input type='button' value='New' id='newBtn' />
		<input type='button' value='Delete' id='deleteBtn' />
		<input type='button' value='Edit' id='editBtn' />
		<input type='button' value='Reload' id='reloadBtn' />
	</div>
	
	<div id='newForm'>
		<form>
  			<fieldset>
				<legend>Create New Record</legend>
				<label for='newFolder'>Folder</label><input type='text' id='newFolder'/><br/>
				<label for='newUrl'>Url</label><input type='text' id='newUrl'/><br/>
  			</fieldset>
			<input type='button' value='Close' id='closeNewForm' />
			<input type='submit' value='Submit'/>
		</form>
	</div>
	
	<div id='editForm'>
		<form>
  			<fieldset>
				<legend>Edit Record</legend>
				<input type='hidden' id='editUrl'/>
				<label for='editFolder'>Folder</label><input type='text' id='editFolder'/><br/>
				<label for='editUrl'>Url</label><input type='text' id='editUrl'/><br/>
			</fieldset>
			<input type='button' value='Close' id='closeEditForm' />
			<input type='submit' value='Submit'/>
		</form>
	</div>
	
	<table id='tableBookmarks'>
		<caption></caption>
		<thead>
			<tr>
				<th></th>
				<th>Folder</th>
				<th>Url</th>
			</tr>
		</thead>
	</table>
	
	
	
</body>
</html>