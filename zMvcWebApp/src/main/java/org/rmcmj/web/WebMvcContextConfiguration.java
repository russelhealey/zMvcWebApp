package org.rmcmj.web;

import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

//@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = { "org.rmcmj" })
//public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {
public class WebMvcContextConfiguration {
	private static final long MAX_FILE_UPLOAD_SIZE = 1024 * 1024 * 5; // 5Mb
																		// file
																		// liimit
	private static final int FILE_SIZE_THRESHOLD = 1024 * 1024; // After 1Mb
																// start writing
																// files to disk

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxInMemorySize(FILE_SIZE_THRESHOLD);
		multipartResolver.setMaxUploadSize(MAX_FILE_UPLOAD_SIZE);
		return multipartResolver;
	}
}
