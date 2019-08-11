package edu.sam.aveng.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:production.app.properties")
@TestPropertySource("classpath:test.app.properties")
public class DataAccessLayerConfig {

    private Environment env;

    @Autowired
    public DataAccessLayerConfig(Environment env) {
        if (env == null) {
            // ToDo: Handle the exception properly
            throw new IllegalArgumentException();
        }
        this.env = env;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(env.getProperty("db.driver"));
        ds.setUrl(env.getProperty("db.url"));
        ds.setUsername(env.getProperty("db.user"));
        ds.setPassword(env.getProperty("db.password"));
        return ds;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {

        Properties hibernateProps = new Properties();
        hibernateProps.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        hibernateProps.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.mode"));

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(env.getProperty("hibernate.entity.package"));
        sessionFactory.setHibernateProperties(hibernateProps);

        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManger(LocalSessionFactoryBean sessionFactory) {
        HibernateTransactionManager tm = new HibernateTransactionManager();
        tm.setSessionFactory(sessionFactory.getObject());
        return tm;
    }
}
