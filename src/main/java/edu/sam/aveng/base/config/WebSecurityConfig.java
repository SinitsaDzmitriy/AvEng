package edu.sam.aveng.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@EnableWebSecurity
@Configuration
@PropertySource("classpath:production.app.properties")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private Environment env;

    @Autowired
    public WebSecurityConfig(Environment env) {
        this.env = env;
    }

    private PasswordEncoder passEncoder;
    private UserDetailsService userDetailsService;

    @Autowired
    public void setPassEncoder(PasswordEncoder passEncoder) {
        this.passEncoder = passEncoder;
    }

    @Autowired
    @Qualifier("popUserServiceImpl")
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding(env.getProperty("request.encoding", "UTF-8"));
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);

        String[] publicUrls = {
                "/", "/initial",
                "/register", "/login",
                "/cards/search", "/cards/display/**",
                "/resources/**"
        };

        String[] additionalUserUrls = {
                "/user_cards/**",
                "/api/user_cards/**"
        };

        http.authorizeRequests()
                .antMatchers(publicUrls)
                .permitAll()
                .antMatchers(additionalUserUrls)
                .hasRole("USER")
                .anyRequest()
                .hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin();

//        // ToDo: Remove temporary config
//        http.authorizeRequests()
//                .anyRequest()
//                .permitAll()
//                .and()
//                .csrf().disable();

    }
}