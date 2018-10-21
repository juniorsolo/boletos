package br.com.juniorcorp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	    	http.authorizeRequests()
	    	.antMatchers("/login","/acessonegado" ).permitAll()
	    	.antMatchers("/","/consulta" , "/verparcelas", "/cadastro","/sucesso","/emAtraso","/parcelasematraso").hasRole("USER")
			//.antMatchers("/**").hasAnyRole("ADMIN","USER")
			.and().formLogin()  //login configuration
	                .loginPage("/login")
	                .loginProcessingUrl("/app-login")
	                .usernameParameter("app_username")
	                .passwordParameter("app_password")
	                .defaultSuccessUrl("/")	
			.and().logout()    //logout configuration
			.logoutUrl("/app-logout") 
			.logoutSuccessUrl("/login")
			.and().exceptionHandling() //exception handling configuration
			.accessDeniedPage("/acessoNegado");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
    
    
}