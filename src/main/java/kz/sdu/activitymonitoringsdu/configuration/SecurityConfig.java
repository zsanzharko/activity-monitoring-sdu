package kz.sdu.activitymonitoringsdu.configuration;

import kz.sdu.activitymonitoringsdu.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDao userDao;

    @Autowired
    public SecurityConfig(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // the boolean flags force the redirection even though
        // the user requested a specific secured resource.
        http.formLogin().defaultSuccessUrl("/index.html", true);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDao);
    }
}
