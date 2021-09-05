package com.blue1.challenge.security;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.session.ConcurrentSessionFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //no sessions are created, add the filter for token verification
        //and exclude the endpoint for token retrieval

        http.csrf().disable().sessionManagement().sessionCreationPolicy(STATELESS);
        http.addFilterBefore(new TokenAuthorizationFilter(), ConcurrentSessionFilter.class);
        //        http.authorizeRequests().anyRequest().permitAll();
        http.authorizeRequests().antMatchers("/security/tokenplease").permitAll();//


    }
}
