package com.luxoft.rcalculator.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthProvider authProvider;

    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    private SecuritySuccessHandler securitySuccessHandler;

    @Autowired
    public void setSecuritySuccessHandler(SecuritySuccessHandler securitySuccessHandler) {
        this.securitySuccessHandler = securitySuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/calculate", "/result", "/enter", "/login**").permitAll()
                .antMatchers("/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .antMatchers("/user/**")
                .access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .successHandler(securitySuccessHandler)
                .usernameParameter("login")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
                .and()
                .csrf().disable()
                .exceptionHandling()
                .accessDeniedPage("/403");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider);
    }
}
