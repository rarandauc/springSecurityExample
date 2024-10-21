package com.example.security.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private UserDetailsService userDetailsService;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests((authorize)->{
                    authorize.requestMatchers("api/v1/**",
                                    "/register*",
                                    "/login")
                            .permitAll();

                    authorize.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());
//http.csrf(Customizer.withDefaults());
        /**
         * http.formLogin(fL -> fL.loginPage("/login")
         *             .usernameParameter("email").permitAll()
         *             .defaultSuccessUrl("/", true)
         *             .failureUrl("/login-error"));
         *     http.logout(logOut -> logOut.logoutUrl("/logout")
         *             .clearAuthentication(true)
         *             .invalidateHttpSession(true)
         *             .deleteCookies("JSESSIONID","Idea-2e8e7cee")
         *             .logoutSuccessUrl("/login"))
         */
        return http.build();
    }

   /* @Bean
    public UserDetailsService userDetailsService(){

        UserDetails john = User.builder()
                .username("john")
                .password(passwordEncoder().encode("john"))
                .roles("USER")
                .build();

        UserDetails sam = User.builder()
                .username("sam")
                .password(passwordEncoder().encode("sam"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john,sam);
    }*/
}