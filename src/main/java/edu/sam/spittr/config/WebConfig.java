package edu.sam.spittr.config;

import org.apache.commons.lang3.CharSetUtils;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan("edu.sam.spittr.controller")
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final String LOCALE_PARAM = "lang";

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

    // Sets up message source (internationalization)
    @Bean
    public MessageSource messageSource() {
        // ResourceBundleMessageSource implements MessageSource interface
        // It loads messages from a properties file whose name is derived from a base name.
        // derive - происходить, bundle - пачка
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator(){
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

    // LocalResolver object resolves messages using a specific local.
    @Bean
    public LocaleResolver localeResolver() {
//        SessionLocaleResolver slr = new SessionLocaleResolver();
//        slr.setDefaultLocale(Locale.ENGLISH);
//        return slr;
        return new SessionLocaleResolver();
    }


    // Interceptor - перехватчик, прерыватель.
    // LocaleChangeInterceptor switches the app to a new locale
    // that is defined in a special param appended to a request.
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName(LOCALE_PARAM);
        return interceptor;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:mem:aveng");
        ds.setUsername("sa");
        ds.setPassword("");
        return ds;
    }

    /*
        Uploaded (media) files are carried as a part of multipart request. This
    part has a binary data in body. DispatcherServlet delegates to an
    implementation of Spring’s MultipartResolver strategy interface to resolve
    the content in a multipart request. Spring comes with
    StandardServletMultipartResolver - the out-of-the-box implementation of
    MultipartResolver. It declares as a bean in Spring configuration. It has no
    properties and no constructor arguments. Multipart configuration must be
    specified in the servlet configuration. The customizeRegistration() method
    which is given a Dynamic servlet registration as a parameter can be
    overridden to configure multipart details.
     */


    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    /*
            SessionFactory is Hibernate’s SessionFactory interface that
        responsible for opening, closing, and managing Hibernate Sessions.
        In Spring, a Hibernate SessionFactory could be got through one
        of Spring’s Hibernate session-factory beans.
            AnnotationSessionFactoryBean can be configured for either
        XML-based mapping or annotation-based mapping. In this case,
        preference given to annotations
    */

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
        sfb.setDataSource(dataSource);
        sfb.setPackagesToScan("edu.sam.spittr.domain");
        Properties props = new Properties();
        props.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
        sfb.setHibernateProperties(props);
        return sfb;
    }

    /*
            Adding exception translation:
            PersistenceExceptionTranslationPostProcessor is a bean
        post-processor that catches platform specific exceptions and
        rethrow them as one of Spring’s unified unchecked exceptions.
     */

    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Override
    /*
     Ask DispatcherServlet to forward requests for static resources to the
     servlet container’s default servlet and not to try to handle them itself
    */
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

        configurer.enable();
    }

    // Adds LocaleChangeInterceptor to InterceptorRegistry.
    // registry - реестр.
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
