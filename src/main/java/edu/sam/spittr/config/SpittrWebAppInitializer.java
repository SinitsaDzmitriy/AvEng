package edu.sam.spittr.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*
 *  Any class that extends AbstractAnnotationConfigDispatcherServletInitializer
 *  will automatically be used to configure DispatcherServlet and the Spring
 *  application context in the application’s servlet context.
 */

public class SpittrWebAppInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    // Identifies one or more paths that DispatcherServlet will be mapped to.
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    // Defines beans represented as @Configuration annotated classes which will be
    // used to configure the application context created by ContextLoaderListener.
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class };
    }

    // Defines beans represented as @Configuration annotated classes which will
    // be load to Dispatcher-Servlet’s application context
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class };
    }
}