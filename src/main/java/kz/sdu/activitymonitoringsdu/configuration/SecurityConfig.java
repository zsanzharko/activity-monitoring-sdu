package kz.sdu.activitymonitoringsdu.configuration;

import kz.sdu.activitymonitoringsdu.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDao userDao;

    @Autowired
    public SecurityConfig(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers((matchers) -> matchers.antMatchers("/static/**"))
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll())
                .authorizeRequests()
                .antMatchers("/admin").hasRole("EMPLOYEE")
                .antMatchers("/user").hasAnyRole("MANAGER", "EMPLOYEE")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll();
    }

    private AuthenticationFailureHandler authenticationFailureHandler() {
        return null;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDao);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
