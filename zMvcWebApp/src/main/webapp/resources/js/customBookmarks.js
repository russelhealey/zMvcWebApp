/**
 * Contains custom JavaScript code
 */


//function loadBookmarkTable() {
//	$.get(bookmarkUrlHolder.records, function(response) {
//		
// 		$('#tableBookmarks').find('tbody').remove();
// 		if(response.bookmarks != null){
//	 		for (var i=0; i<response.bookmarks.length; i++) {
//				var row = '<tr>';
//				row += '<td><input type="radio" name="index" id="index" value="'+i+'"></td>';
//				row += '<td>' + response.bookmarks[i].folder + '</td>';
//				row += '<td>' + response.bookmarks[i].url + '</td>';
//				row += '</tr>';
//		 		$('#tableBookmarks').append(row);
//	 		}
// 		}
// 		$('#tableBookmarks').data('model', response.bookmarks);
//		toggleBookmarkForms('hide'); ;
// 	});
//}

function loadBookmarkTable() {
	
	$.ajax({
		type: "get",
		  url: bookmarkUrlHolder.records,
		  async: false,
		  success: function ( response, textStatus, jqXHR ) {
			  $('#tableBookmarks').find('tbody').remove();
		 		if(response.bookmarks != null){
			 		for (var i=0; i<response.bookmarks.length; i++) {
						var row = '<tr>';
						row += '<td><input type="radio" name="index" id="index" value="'+i+'"></td>';
						row += '<td>' + response.bookmarks[i].folder + '</td>';
						row += '<td>' + response.bookmarks[i].url + '</td>';
						row += '</tr>';
				 		$('#tableBookmarks').append(row);
			 		}
		 		}
		 		$('#tableBookmarks').data('model', response.bookmarks);
				toggleBookmarkForms('hide'); ;
		  },
		  error: function (jqXHR, textStatus, errorThrown) {
			  alert('error' + ' ' + textStatus + ' ' +  errorThrown);
		  }
	});
}
	
//	function submitNewBookmarkRecord() {
//		
//		$.post(bookmarkUrlHolder.add, {
//				folder: $('#newFolder').val(),
//				url: $('#newUrl').val()
//			}, 
//			function(response) {
//				alert('Success!');
//				if (response != null) {
//					loadBookmarkTable();
//					toggleBookmarkForms('hide'); ;
//					toggleCrudBookmarkButtons('show');
//					
//				} else {
//					alert('Failure! An error has occurred!');
//				}
//			}
//		);	
//		alert('Record has been added.');
//	}
	
	function submitNewBookmarkRecord() {
		$.ajax({
			type: "post",
			  url: bookmarkUrlHolder.add,
			  data: {
					folder: $('#newFolder').val(),
					url: $('#newUrl').val()
			  },
			  async: false,
			  success: function ( response, textStatus, jqXHR ) {
				loadBookmarkTable();
				toggleBookmarkForms('hide'); ;
				toggleCrudBookmarkButtons('show');
			  },
			  error: function (jqXHR, textStatus, errorThrown) {
				  alert('error' + ' ' + textStatus + ' ' +  errorThrown);
			  }
		});
	}
	
	function submitDeleteBookmarkRecord() {
		var obj = $('input:radio[name=index]:checked');
		var selected = $('input:radio[name=index]:checked').val();
		
		$.ajax({
			type: "post",
			url: bookmarkUrlHolder.del, 
			data: {
				url: $('#tableBookmarks').data('model')[selected].url
			},
			async: false,
			success: function (response, textStatus, jqXHR) {
				loadBookmarkTable();
			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert('error' + ' ' + textStatus + ' ' +  errorThrown);
			},
			complete: function(){
				alert('Record has been deleted.');
			}
		});
		
	}

//function submitDeleteBookmarkRecord() {
//	var selected = $('input:radio[name=index]:checked').val();
//	
//	$.post(bookmarkUrlHolder.del, {
//			url: $('#tableBookmarks').data('model')[selected].url
//		}, 
//		function(response) {
//			if (response == true) {
//				loadBookmarkTable();
//				alert('Success!');
//			} else {
//				alert('Failure! An error has occurred!');
//			}
//		}
//	);
//	alert('Record has been deleted.');
//}

	//function submitDeleteBookmarkRecord() {
//	var selected = $('input:radio[name=index]:checked').val();
//	
//	$.post(bookmarkUrlHolder.del, {
//			url: $('#tableBookmarks').data('model')[selected].url
//		}, 
//		function(response) {
//			if (response == true) {
//				loadBookmarkTable();
//				alert('Success!');
//			} else {
//				alert('Failure! An error has occurred!');
//			}
//		}
//	);
//	alert('Record has been deleted.');
//}

	function submitUpdateBookmarkRecord() {
		$.ajax({
			type: "post",
			url: bookmarkUrlHolder.edit, 
			data: {
				folder: $('#editFolder').val(),
				url: $('#editUrl').val()
			},
			async: false,
			success: function (response, textStatus, jqXHR) {
				loadBookmarkTable();
				toggleBookmarkForms('hide'); ;
				toggleCrudBookmarkButtons('show');
				
			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert('error' + ' ' + textStatus + ' ' +  errorThrown);
			}
		}
		
		);
	}

	function hasBookmarkSelected() {
		var selected = $('input:radio[name=index]:checked').val();
		if (selected == undefined) { 
			alert('Select a record first!');
			return false;
		}
		
		return true;
	}

	function fillEditBookmarkForm() {
		var selected = $('input:radio[name=index]:checked').val();
		$('#editFoler').val( $('#tableBookmarks').data('model')[selected].folder );
		$('#editUrl').val( $('#tableBookmarks').data('model')[selected].url );
	}

	function resetNewBookmarkForm() {
		$('#newFolder').val('');
		$('#newUrl').val('');
	}

	function resetEditBookmarkForm() {
		$('#editFolder').val('');
		$('#editUrl').val('');
	}

	function toggleBookmarkForms(id) {
		if (id == 'hide') {
			$('#newForm').hide();
			$('#editForm').hide();
			
		} else if (id == 'new') {
	 		resetNewBookmarkForm();
	 		$('#newForm').show();
	 		$('#editForm').hide();
	 		
		} else if (id == 'edit') {
	 		resetEditBookmarkForm();
	 		$('#newForm').hide();
	 		$('#editForm').show();
		}
	}

	function toggleCrudBookmarkButtons(id) {
		if (id == 'show') {
			$('#newBtn').removeAttr('disabled');
			$('#editBtn').removeAttr('disabled');
			$('#deleteBtn').removeAttr('disabled');
			$('#reloadBtn').removeAttr('disabled');
		} else if (id == 'hide'){
			$('#newBtn').attr('disabled', 'disabled');
			$('#editBtn').attr('disabled', 'disabled');
			$('#deleteBtn').attr('disabled', 'disabled');
			$('#reloadBtn').attr('disabled', 'disabled');
		}
	}