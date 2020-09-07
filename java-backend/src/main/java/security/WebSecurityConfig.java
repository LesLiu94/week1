package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	*
	* To avoid using UserDetailsServiceAutoConfiguration.clas for user authentication,
    * the following configurations need to be written for our app:
	 *
	* 1) AuthenticationManager.class
	* 2) AuthenticationProvider.class
	* 3) UserDetailsService.class (done)
	*
	* */
    @Autowired
    DataSource dataSource;

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.jdbcAuthentication().dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
	        .authorizeRequests()
		        .anyRequest().authenticated()
				.and()
			.httpBasic();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}