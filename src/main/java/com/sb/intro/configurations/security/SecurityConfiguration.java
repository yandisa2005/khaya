package com.sb.intro.configurations.security;

import com.sb.intro.repositories.UserRepository;
import com.sb.intro.security.Http401UnauthorizedEntryPoint;
import com.sb.intro.security.xauth.TokenProvider;
import com.sb.intro.security.xauth.XAuthTokenConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import static com.sb.intro.security.AuthoritiesConstants.ADMIN;
import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
//@EnableConfigurationProperties(SbsaConfiguration.ActiveDirectory.class)
@Profile("!local")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

   private Http401UnauthorizedEntryPoint authenticationEntryPoint;
//
//    @Autowired
//    private UserRepository userRepository;

//    @Autowired
//    private AuthorityRepository authorityRepository;
//
//    @Autowired
//    private SbsaConfiguration.ActiveDirectory configuration;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenProvider tokenProvider;

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        SbsaMultiDomainAuthenticationProvider provider = new SbsaMultiDomainAuthenticationProvider(configuration.getUrl(), configuration.getDomains());
//        provider.setUserDetailsContextMapper(new ActiveDirectoryUserDetailsContextMapper(userRepository, authorityRepository));
//        auth.authenticationProvider(provider);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint)
            .and()
                .csrf()
                .disable()
                 .headers()
                    .frameOptions()
                    .disable()
                .sessionManagement()
                    .sessionCreationPolicy(STATELESS)
            .and()
                .httpBasic()
            .and()
                .authorizeRequests()
                    .antMatchers(OPTIONS, "/**").permitAll()
                    .antMatchers("/api/authenticate").permitAll()
                    .antMatchers("/docs/**").permitAll()
                    .antMatchers("/api/**").authenticated()
                    .antMatchers("/events").permitAll()
                    .antMatchers("/account").permitAll()
                    .antMatchers("/users").permitAll()
//                .antMatchers("/processes/**").authenticated()
                .antMatchers("/events").hasAuthority(ADMIN)
                .antMatchers("/users").hasAuthority(ADMIN)
//                .antMatchers("/rules/**").hasAuthority(ADMIN)
//                .antMatchers("/api/users/").hasAuthority(ADMIN)
//                .antMatchers("/processes/systems/").hasAuthority(ADMIN)
//                .antMatchers("/processes/definitions").hasAuthority(ADMIN)
//                .antMatchers("/websocket/**").permitAll()
//                .antMatchers("/topic/**").permitAll()
            .and()
                .apply(new XAuthTokenConfigurer(userDetailsService, tokenProvider));
    }
}
