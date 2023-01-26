//package com.oltoch.app.wss.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import com.oltoch.app.wss.service.UserService;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurity {
//    private final UserService detailsService;
//    private final BCryptPasswordEncoder passwordEncoder;
//
//    public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
//        detailsService = userDetailsService;
//        passwordEncoder = bCryptPasswordEncoder;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeHttpRequests((requests) -> requests
//                .requestMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL)
//                .permitAll().anyRequest().authenticated()
//
//        );
//
//        return http.build();
//
//    }
//
////	@Bean
////	public AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
////		auth.userDetailsService(detailsService).passwordEncoder(passwordEncoder);
////		return auth.build();
////	}
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
//            throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//}
