package org.rmcmj.controller;

import java.beans.XMLEncoder;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.rmcmj.domain.Bookmark;
import org.rmcmj.dto.BookmarkListDto;
import org.rmcmj.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/bookmarks")
public class BookmarkController {

	@Autowired
	private BookmarkService service;

	@RequestMapping(value = "/records")
	public @ResponseBody
	BookmarkListDto getBookmarks() {
		BookmarkListDto bookmarkListDto = new BookmarkListDto();
		if (AuthenticationHelper.loggedIn()) {
			bookmarkListDto.setBookmarks(service.readAll());
		}

		return bookmarkListDto;
	}

	@RequestMapping
	public String getBookmarksPage() {
		return "bookmarks";
	}

	@RequestMapping(value = "/get")
	public @ResponseBody
	Bookmark get(@RequestBody Bookmark bookmark) {

		return service.read(bookmark);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody
	Bookmark create(@RequestParam String folder, @RequestParam String url) {
		Bookmark bookmark = new Bookmark();
		bookmark.setFolder(folder);
		bookmark.setUrl(url);

		return service.create(bookmark);
	}

	private Bookmark findByUrl(String url) {
		List<Bookmark> readAll = service.readAll();
		for (Bookmark bookmark : readAll) {
			if (bookmark.getUrl().equals(url)) {
				return bookmark;
			}
		}
		return null;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody
	Bookmark update(@RequestParam String foler, @RequestParam String url) {
		Bookmark bookmark = this.findByUrl(url);
		return service.update(bookmark);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Boolean delete(@RequestParam String url) {

		Bookmark bookmark = this.findByUrl(url);
		service.delete(bookmark);

		return service.delete(bookmark);
	}

	@RequestMapping("/download")
	public void downloadFiles(HttpServletResponse response) throws IOException {
		// response.setContentType("text/plain");
		// PrintWriter out = response.getWriter();
		// out.println("System Properties:");
		// for (Map.Entry<Object, Object> property : System.getProperties()
		// .entrySet()) {
		// out.println(property.getKey() + ": " + property.getValue());
		// }
		// out.println();
		// out.println("System Environment:");
		// for (Map.Entry<String, String> envvar : System.getenv().entrySet()) {
		// out.println(envvar.getKey() + ": " + envvar.getValue());
		// }

		response.setContentType("text/plain");
		ServletOutputStream outputStream = response.getOutputStream();

		XMLEncoder xmlEncoder = new XMLEncoder(outputStream);

		List<Bookmark> all = service.readAll();
		xmlEncoder.writeObject(all);
		// for (Bookmark bookmark : all) {
		// xmlEncoder.writeObject(bookmark);
		// }
		xmlEncoder.flush();
		xmlEncoder.close();
		outputStream.flush();
		outputStream.close();

	}
	// @RequestMapping(value = "/upload", method = RequestMethod.POST)
	// public void upload(HttpServletRequest request) throws IOException {
	// String contentType = request.getContentType();
	// if ((contentType != null)
	// && (contentType.indexOf("multipart/form-data") >= 0)) {
	// DataInputStream in = new DataInputStream(request.getInputStream());
	// // we are taking the length of Content type data
	// int formDataLength = request.getContentLength();
	// byte dataBytes[] = new byte[formDataLength];
	// int byteRead = 0;
	// int totalBytesRead = 0;
	// // this loop converting the uploaded file into byte code
	// while (totalBytesRead < formDataLength) {
	// byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
	// totalBytesRead += byteRead;
	// }
	// String file = new String(dataBytes);
	// // for saving the file name
	// String saveFile = file.substring(file.indexOf("filename=\"") + 10);
	// saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
	// saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1,
	// saveFile.indexOf("\""));
	// int lastIndex = contentType.lastIndexOf("=");
	// String boundary = contentType.substring(lastIndex + 1,
	// contentType.length());
	// int pos;
	// // extracting the index of file
	// pos = file.indexOf("filename=\"");
	// pos = file.indexOf("\n", pos) + 1;
	// pos = file.indexOf("\n", pos) + 1;
	// pos = file.indexOf("\n", pos) + 1;
	// int boundaryLocation = file.indexOf(boundary, pos) - 4;
	// int startPos = ((file.substring(0, pos)).getBytes()).length;
	// int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;
	// // creating a new file with the same name and writing the content in
	// // new file
	// FileOutputStream fileOut = new FileOutputStream(saveFile);
	// fileOut.write(dataBytes, startPos, (endPos - startPos));
	// fileOut.flush();
	// fileOut.close();
	// }
	// }

	// @RequestMapping(value = "/upload", method = RequestMethod.POST)
	// public String upload(MultipartFile request) {
	//
	// InputStream inputStream;
	// try {
	// inputStream = request.getInputStream();
	// XMLDecoder xmlDecoder = new XMLDecoder(inputStream);
	// List<Bookmark> bookmarks = (List<Bookmark>) xmlDecoder.readObject();
	//
	// service.deleteAll();
	// service.saveAll(bookmarks);
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	// return null;
	// }

}
