package net.fittable.admin.infrastructure.configurations;

import net.fittable.admin.infrastructure.components.security.DatabaseAccountAuthProvider;
import net.fittable.admin.infrastructure.components.security.filter.FormLoginFilter;
import net.fittable.admin.infrastructure.components.security.handler.LoginFailureHandler;
import net.fittable.admin.infrastructure.components.security.handler.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static final String LOGIN_REQUEST_ENDPOINT = "/login";

    @Autowired
    private DatabaseAccountAuthProvider authProvider;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    private FormLoginFilter formLoginFilter() {
        return new FormLoginFilter(LOGIN_REQUEST_ENDPOINT, this.loginSuccessHandler, this.loginFailureHandler);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(this.authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

        http
                .csrf().disable();

        http
                .headers().frameOptions().disable();

        http
                .addFilterBefore(formLoginFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
