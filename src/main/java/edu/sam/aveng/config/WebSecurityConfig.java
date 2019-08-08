package edu.sam.aveng.config;

import org.springframework.beans.factory.annotation.Autowired;
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
@PropertySource("classpath:realise.app.properties")
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
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(16);
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

        http.authorizeRequests()
//                .antMatchers("/card/**")
//                .hasRole("USER")
//                .antMatchers("/", "/initial", "/login", "/registration")
//                .permitAll()
//                .and()
//                .formLogin();
                .anyRequest()
                .permitAll()
                .and()
                .csrf()
                .disable();
                /*
                        Disable csrf protection (enabled by default in Spring
                    Security) to test REST controller with Postman.
                */
    }
}