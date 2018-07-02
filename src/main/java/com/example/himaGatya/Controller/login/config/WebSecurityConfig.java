package com.example.himaGatya.Controller.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.himaGatya.Controller.login.CertificationsService;



@EnableWebSecurity
@EnableScheduling
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CertificationsService certificationService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers( "/signup", "/login", "/login-error").permitAll()
                .antMatchers("/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated().and().csrf().disable().formLogin()
                .loginPage("/login").failureUrl("/login?error=true")
                .defaultSuccessUrl("/")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
            .formLogin()
                .loginPage("/login").failureUrl("/login-error").defaultSuccessUrl("/");
        http.logout()
        	.logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))       // ログアウト処理のパス
        	.logoutSuccessUrl("/").invalidateHttpSession(true).deleteCookies("JSESSIONID")
        	.permitAll();  
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
        	.userDetailsService(certificationService)
        	.passwordEncoder(passwordEncoder());
        
        certificationService.registerUser("username", "password", "mail@Address");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

}


