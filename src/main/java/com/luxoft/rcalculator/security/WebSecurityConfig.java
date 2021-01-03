package com.luxoft.rcalculator.security;

import com.luxoft.rcalculator.dao.RoleRepository;
import com.luxoft.rcalculator.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthProvider authProvider;

    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private SecuritySuccessHandler securitySuccessHandler;

    @Autowired
    public void setSecuritySuccessHandler(SecuritySuccessHandler securitySuccessHandler) {
        this.securitySuccessHandler = securitySuccessHandler;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/login**").permitAll()
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
