package com.itheima.chapter07.config;

import com.itheima.chapter07.entity.UserDto;
import com.itheima.chapter07.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/img/**", "/loginview", "/customLogin").permitAll()
                .antMatchers("/book/admin/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/loginview")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/loginview")
                .permitAll()
                .and()
            .headers()
                .frameOptions().sameOrigin()
                .and()
            .csrf().disable();
    }

    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                Authentication authentication) throws IOException, ServletException {
                System.out.println("登录成功处理器: 用户=" + authentication.getName());
                
                // 查询用户信息并存入session
                UserDto userDto = userMapper.getUserByUsername(authentication.getName());
                if (userDto != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", userDto);
                    System.out.println("用户信息已存入session: " + userDto.getUsername() + ", " + userDto.getName());
                }
                
                // 重定向到首页
                response.sendRedirect(request.getContextPath() + "/");
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
} 