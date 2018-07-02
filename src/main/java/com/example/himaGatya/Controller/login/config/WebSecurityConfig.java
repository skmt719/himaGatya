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
    	// 認可の設定
    		http.authorizeRequests()
                .antMatchers( "/signUp", "/login", "/login-error").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/home/**").hasRole("USER")
                .antMatchers("/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated().and().csrf().disable();
    	// ログイン設定
            http.formLogin()
                .loginPage("/login").failureUrl("/login-error")
                .defaultSuccessUrl("/home")
                .usernameParameter("username")
                .passwordParameter("password")
                .and();
         // ログアウト設定
	        http.logout()
	        	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))       // ログアウト処理のパス
	        	.logoutSuccessUrl("/login").invalidateHttpSession(true).deleteCookies("JSESSIONID")
	        	.permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
        	.userDetailsService(certificationService)
        	.passwordEncoder(passwordEncoder());

        //certificationService.registerUser("username", "password", "mail@Address");
        //certificationService.registerAdmin("admin", "adminadmin", "admin@admin");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

}


