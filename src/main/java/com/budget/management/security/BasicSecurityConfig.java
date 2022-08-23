package com.budget.management.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.http.HttpMethod;

//@EnableWebSecurity
public class BasicSecurityConfig {//extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.userDetailsService(userDetailsService);
//        auth.inMemoryAuthentication()
//                .withUser("root")
//                .password(passwordEncoder().encode("root"))
//                .authorities("ROLE_USER");
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http)
//            throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/user/login").permitAll()
//                .antMatchers("/user/register").permitAll()
//                .antMatchers(HttpMethod.OPTIONS).permitAll()
//                .anyRequest().authenticated()
//                .and().httpBasic()
//                .and().sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().cors()
//                .and().csrf().disable();
//    }

    }
