	function uploadBookmarks() {
		$.ajax({
			type: "get",
			  url: uploadBookmarkUrlHolder.submit,
			  data: {
					bookmarks: $('#bookmarkXmlTxtArea').val()
			  },
			  async: false,
			  success: function ( response, textStatus, jqXHR ) {
				alert('Uploaded Bookmarks');
			  },
			  error: function (jqXHR, textStatus, errorThrown) {
				  alert('error' + ' ' + textStatus + ' ' +  errorThrown);
			  }
		});
	}