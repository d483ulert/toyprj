package com.my.toyprj.security.config;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests()
                .antMatchers("/main").permitAll()
                .antMatchers("/login/index").permitAll()
                .anyRequest().authenticated();

/* httpSecurity.authorizeHttpRequests()
    .antMatchers("/user/**").authenticated()
    .antMatchers("/admin/**").hasRole("ADMIN")
    .and()
    .formLogin()
    .loginPage("/login/index")
    .successHandler(authenticationSuccessHandler())
    .usernameParameter("userId")
    .passwordParameter("passwd")
    .loginProcessingUrl("/login/in")
    .defaultSuccessUrl("/main")
    .failureUrl("/login/fail")
    .and()
    .logout()
    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    .logoutSuccessUrl("/login/logout")
    .invalidateHttpSession(true);

httpSecurity.csrf().disable();
httpSecurity.exceptionHandling().accessDeniedPage("/disallow");
*/
    }


    @Override
    protected void configure(AuthenticationManagerBuilder amb)  throws Exception{
        amb.inMemoryAuthentication()
            .withUser("jsAdmin")
            .password("1q2w3e4r@@")
            .roles("ADMIN");

        amb.inMemoryAuthentication()
            .withUser("jsMember")
            .password("1q2w3e4r@@")
            .roles("MEMBER");

    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new CustomLoginSuccessHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

