package edu.sam.aveng.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import java.nio.charset.StandardCharsets;

/**
 * Instance of that class is automatically used to configure {@code DispatcherServlet}
 * and Spring context in the app {@code Servlet} context since it extends
 * {@link org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer
 * AbstractAnnotationConfigDispatcherServletInitializer}.
 *
 * @author Dzmitry Sinitsa
 * @since AvEng 1.0
 */
public class WebAppInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{new CharacterEncodingFilter(StandardCharsets.UTF_8.name())};
    }
}