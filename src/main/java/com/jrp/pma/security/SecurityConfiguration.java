package com.jrp.pma.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan("com.baeldung.security")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource datasource;
	
	@Autowired
    BCryptPasswordEncoder webConfig;
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.jdbcAuthentication().dataSource(datasource)
    	.usersByUsernameQuery("select username, password, enable  "
    			+ " from user_accounts where username = ?")
    	.authoritiesByUsernameQuery("select username, role  "
    			+ " from user_accounts  where username = ?")
    	.passwordEncoder(webConfig);
    }
 
	//in Memory
/*    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.inMemoryAuthentication()
		.withUser("myuser")
			.password("pass")
			.roles("USER")
		.and()
		.withUser("taz")
			.password("pass2")
			.roles("USER")
		.and()
		.withUser("admin")
		.password("admin")
		.roles("ADMIN");
    }
*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
        antMatchers("/projects/new").hasRole("ADMIN")
        .antMatchers("/employees/new").hasRole("ADMIN")
        .antMatchers("/").authenticated().and().formLogin();
        
        http.csrf().disable();
        http.headers().frameOptions().disable();
            
    }
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}