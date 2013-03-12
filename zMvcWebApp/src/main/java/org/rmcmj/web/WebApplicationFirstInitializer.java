package org.rmcmj.web;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

//public class WebApplicationFirstInitializer implements
//		WebApplicationInitializer {
public class WebApplicationFirstInitializer {

	private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

	private static final long MAX_FILE_UPLOAD_SIZE = 1024 * 1024 * 5; // 5 Mb
																		// file
																		// limit
	private static final int FILE_SIZE_THRESHOLD = 1024 * 1024; // After 1Mb
																// start writing
																// files to disk
	private static final long MAX_REQUEST_SIZE = -1L; // No request size limit

	// @Override
	// public void onStartup(ServletContext servletContext)
	// throws ServletException {
	// registerDispatcherServlet(servletContext);
	//
	// }

	// private void registerDispatcherServlet(ServletContext servletContext) {
	// AnnotationConfigWebApplicationContext dispatcherContext =
	// createContext(WebMvcContextConfiguration.class);
	//
	// ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
	// DISPATCHER_SERVLET_NAME, new DispatcherServlet(
	// dispatcherContext));
	// dispatcher.setLoadOnStartup(1);
	// dispatcher.addMapping("/");
	//
	// dispatcher.setMultipartConfig(new MultipartConfigElement(null,
	// MAX_FILE_UPLOAD_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD));
	// }

	/**
	 * Factory method to create {@link AnnotationConfigWebApplicationContext}
	 * instances.
	 * 
	 * @param annotatedClasses
	 * @return
	 */
	private AnnotationConfigWebApplicationContext createContext(
			final Class<?>... annotatedClasses) {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(annotatedClasses);
		return context;
	}

}
