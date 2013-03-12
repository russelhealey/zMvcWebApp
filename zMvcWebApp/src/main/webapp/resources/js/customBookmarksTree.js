

function open_win(url){
	window.open(url);
}

function loadTreeTable() {
	$.get(urlHolder.records, function(response) {
 		// $('#tableBookmarks').find('tbody').remove();
 		if(response.bookmarks != null){
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
				row += '<li id="urlItem' + i + '"><input type="button" value="' + response.bookmarks[i].url + '" onclick="open_win( \'' + response.bookmarks[i].url + '\')" /></li>';
		 		if(response.bookmarks.length == (i + 1)){
		 			row += '</ul></li></ul>';
		 		}
		 		previousFolder = folder;
	 		}
	 		$('#bookmarksDiv').append(row);
	 		$("#bookmarksDiv")
			// call `.jstree` with the options object
			.jstree({
				// the `plugins` array allows you to configure the active plugins on this instance
				"plugins" : ["themes","html_data","ui","crrm","hotkeys"]
			})
			// EVENTS
			// each instance triggers its own events - to process those listen on the container
			// all events are in the `.jstree` namespace
			// so listen for `function_name`.`jstree` - you can function names from the docs
			.bind("loaded.jstree", function (event, data) {
				// you get two params - event & data - check the core docs for a detailed description
			});
 		}
 		
 	});
}