/**
 * 
 */
package org.awaa.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.awaa.controller.security.CorsFilter;
import org.awaa.spring.config.WebConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author john
 *
 */
public class Web implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(WebConfig.class);

		// Manage the lifecycle of the root application context
		container.addListener(new ContextLoaderListener(rootContext));

		// Create the dispatcher servlet's Spring application context
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(WebConfig.class);

		// Register and map the dispatcher servlet
		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher",
				new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");

		container.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"))
				.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");

		Dynamic corsFilter = container.addFilter("CorsFilter", CorsFilter.class);
		corsFilter.setInitParameter("targetBeanName", "CorsFilter");
		corsFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
	}

}
