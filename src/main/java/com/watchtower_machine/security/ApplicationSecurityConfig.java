package com.watchtower_machine.security;

import com.watchtower_machine.auth.ApplicationUserService;
import com.watchtower_machine.jwt.JwtConfig;
import com.watchtower_machine.jwt.JwtTokenVerifier;
import com.watchtower_machine.jwt.JwtUsernameAndPasswordAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.SecretKey;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;


    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder,
                                     ApplicationUserService applicationUserService,
                                     SecretKey secretKey,
                                     JwtConfig jwtConfig) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /*Stuff for JSON Web Token*/
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .csrf().disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthFilter(authenticationManager(), jwtConfig, secretKey)) //<--- Dispo pck on extend WebSecurityConfigurerAdapter qui a cette méthode.
                .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig), JwtUsernameAndPasswordAuthFilter.class)
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "js/*","/the_watch_tower/*").permitAll()
                .anyRequest()
                .authenticated()


        /*All the stuff for Form Based and Basic auth*/
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
//                .antMatchers(HttpMethod.POST,("/management/the_watch_tower/**")).hasRole(ADMIN.name())
//                .antMatchers(HttpMethod.PUT,("/management/the_watch_tower/**")).hasRole(ADMIN.name())
//                .antMatchers(HttpMethod.DELETE,("/management/the_watch_tower/**")).hasRole(ADMIN.name())
//                .antMatchers(("/the_watch_tower/getAll")).hasRole(BASIC_USER.name())
//                .antMatchers(("/the_watch_tower/getAll")).hasAnyAuthority(USER_READ.getPermission())
//                .antMatchers(HttpMethod.POST,"/management/the_watch_tower/**").hasAnyAuthority(SPOT_ADD.getPermission())
//                .antMatchers(HttpMethod.POST, "/management/the_watch_tower/**").hasAnyAuthority(USER_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT, "/management/the_watch_tower/**").hasAnyAuthority(USER_WRITE.getPermission())
//                .antMatchers(HttpMethod.GET, "/management/the_watch_tower/**").hasAnyAuthority(USER_READ.getPermission())
//                .and()
//                .httpBasic(); //<----- Basic Auth
//                .formLogin()//<---- Form Based auth
//                .loginPage("/login").permitAll()
//                .defaultSuccessUrl("http://localhost:8080/the_watch_tower/getAll", true)
//                .and()
//                .rememberMe()
//                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21)) //<---- Remembers for 2 weeks
//                    .key("somethingThatShouldBeVerySecured")
//                .and()
//                .logout()
//                    .logoutUrl("/the_watch_tower/logout")
//                    .clearAuthentication(true)
//                    .invalidateHttpSession(true)
//                    .deleteCookies("JSESSIONID")
//                    .logoutSuccessUrl("/login")

        ;
    }

/*Méthode de base Override, mais on a créé nos propres services  et DAO custom qui font la même chose et
 permettent de fetch dans une database.*/
//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails bhodi = User.builder()
//                .username("Bhodi")
//                .password(passwordEncoder.encode("swayze"))
//                .roles(BASIC_USER.name())
//                .authorities(BASIC_USER.getGrantedAuthorities())
//                .build();
//
//        UserDetails quentin = User.builder()
//                .username("Quentin")
//                .password(passwordEncoder.encode("swayze"))
//                .roles(ADMIN.name())
//                .authorities(ADMIN.getGrantedAuthorities())
//                .build();
//
//        UserDetails dudeThatCanOnlyRead = User.builder()
//                .username("reader")
//                .password(passwordEncoder.encode("swayze"))
//                .roles(READER.name())
//                .authorities(READER.getGrantedAuthorities())
//                .build();
//
//        UserDetails dudeThatCanOnlyWrite = User.builder()
//                .username("writer")
//                .password(passwordEncoder.encode("swayze"))
//                .roles(WRITER.name())
//                .authorities(WRITER.getGrantedAuthorities())
//                .build();
//
//        return new InMemoryUserDetailsManager(
//                bhodi,
//                quentin,
//                dudeThatCanOnlyRead,
//                dudeThatCanOnlyWrite
//        );
//    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
