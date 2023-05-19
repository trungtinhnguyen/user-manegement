package org.example.config;

import org.example.security.CustomSuccessHandler;
import org.example.service.impl.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailService customUserDetailService;
    @Autowired
    public SecurityConfig(CustomUserDetailService customUserDetailService) {
        this.customUserDetailService = customUserDetailService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").access("hasRole ('ADMIN')")
                .antMatchers("/home/*").access("hasRole('CUSTOMER')")
                .antMatchers("/**").permitAll()
                .and().formLogin().loginPage("/login")
                .passwordParameter("username").passwordParameter("password")
                .loginProcessingUrl("/j_spring_security_check")
                .failureUrl("/login?success=false")
                .successHandler(customSuccessHandler())
                .and().sessionManagement().invalidSessionUrl("/login?session=invalid")
                .and().exceptionHandling().accessDeniedPage("/access-denied")
                .and().authenticationProvider(daoAuthenticationProvider());
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider () {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(bcryptEncoder());
        daoAuthenticationProvider.setUserDetailsService(customUserDetailService);
        return daoAuthenticationProvider;
    }
    @Bean
    public BCryptPasswordEncoder bcryptEncoder () {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public CustomSuccessHandler customSuccessHandler () {
        return new CustomSuccessHandler();
    }
}
