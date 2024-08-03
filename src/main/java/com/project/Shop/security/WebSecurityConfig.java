package com.project.Shop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }





    @Configuration
    public static class App2ConfiguartionAdapter {
        @Bean
        public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {
        	// tat bao ve CRSF
            http.csrf().disable().authorizeRequests()
//                    .antMatchers("/shoping-cart/**", "/cart-status/**", "/profile")
//                    .authenticated()
//                    .antMatchers("/admin/**").hasAnyRole("ADMIN", "EMPLOYEE")
//                    .antMatchers("/admin-only/**").hasRole("ADMIN")
                    .anyRequest().permitAll()
                    .and()
                    .formLogin()
                    .loginPage("/user-login").loginProcessingUrl("/user_login") // sau khi dang nhap thi di vao day
                    .usernameParameter("email")
                    .defaultSuccessUrl("/") //url mac dinh khi dang nhap thanh cong
                    .successHandler(successHandler()).permitAll()
                    .and().logout()
                    .logoutUrl("/user_logout")
                    .logoutSuccessUrl("/")
                    .permitAll()
                    .and().rememberMe()
                    .key("AbcDefgHijklmnOp_123456789")
                    .rememberMeParameter("remember-me")
                    .tokenValiditySeconds(7 * 24 * 60 * 60);
            http.headers().frameOptions().disable();
            return http.build();

        }

        @Bean
        public AuthenticationSuccessHandler successHandler() {
            SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
            successHandler.setDefaultTargetUrl("/"); // Đường dẫn mặc định nếu không có SavedRequest

            return successHandler;
        }
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().antMatchers("/img/**", "/js/**", "/css/**", "/fonts/**", "/plugins/**", "/error");
//    }
}
