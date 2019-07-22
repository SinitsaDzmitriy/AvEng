package edu.sam.spittr.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import java.util.Properties;

/*
 *  Any class that extends AbstractAnnotationConfigDispatcherServletInitializer
 *  will automatically be used to configure DispatcherServlet and the Spring
 *  application context in the application’s servlet context.
 */

public class SpittrWebAppInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    private Properties properties;

    public SpittrWebAppInitializer() {
        super();
        try {
            properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Identifies one or more paths that DispatcherServlet will be mapped to.
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    // Defines beans represented as @Configuration annotated classes which will be
    // used to configure the application context created by ContextLoaderListener.
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    // Defines beans represented as @Configuration annotated classes which will
    // be load to Dispatcher-Servlet’s application context
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{new CharacterEncodingFilter(properties.getProperty("encoding"))};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(
                // ToDo: Bear this values in a property file
                new MultipartConfigElement("C:/TestStorage/Temporary", 2097152, 4194304, 0));
    }
}