package edu.sam.spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        PasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String password = encoder.encode("password");

        /*
			Enable in memory based authentication with users named
		"user" and "admin".
		*/

        auth.inMemoryAuthentication()
                .withUser("user")
                .password(password)
                .roles("USER")
                .and()
                .withUser("admin")
                .password(password)
                .roles("USER", "ADMIN");
    }

    // Expose the UserDetailsService as a Bean

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ADMIN");
        return super.userDetailsServiceBean();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(16);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/spittr/greeting")
                    .permitAll()
                .antMatchers("/spittr/spittles/**")
                    .hasRole("ADMIN")
                .anyRequest()
                    .authenticated()
                    .and()
                .formLogin();
    }
}