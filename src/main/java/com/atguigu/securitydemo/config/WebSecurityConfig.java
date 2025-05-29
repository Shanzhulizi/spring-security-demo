package com.atguigu.securitydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration//配置类
@EnableWebSecurity//开启springSecurity的自定义配置，在springboot项目中可以省略
public class WebSecurityConfig {

//    @Bean
//    public UserDetailsService userDetailsService() {
////        创建基于内存的用户管理器
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
////      使用manager管理UserDetails对象
//        manager.createUser(
//                //        创建userDetails对象，用于管理用户名，密码，角色，权限
//                User.withDefaultPasswordEncoder()
//                        .username("1234").password("1234")
//                        .roles("USER").build()
//        );
//        return manager;
//    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
//                        // 放行登录页、静态资源等
//                        .requestMatchers("/login", "/css/**", "/js/**").permitAll()
                        // 其他都要认证
                        .anyRequest()
                        .authenticated()//已认证的请求才会被自动授权
                )
                .formLogin(form -> {
                    form.loginPage("/login").permitAll();//无需授权即可访问当前页面
                })
                .csrf(csrf -> csrf.disable()); // 开发时可先关闭 CSRF，生产环境建议开启
        //这里不关会在使用swagger时返回403

        return http.build();

    }


/**
 * 这里因为DBUserDetailsManager.java已经使用@Component
 * 你手动 new 出的 Bean 没有被 Spring 注入依赖（数据库操作、密码加密），所以它功能是空的，是个“空壳”。你要让 Spring 自动创建这个对象，依赖才会注入进来。
 */
    //    基于数据库的用户认证
//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        DBUserDetailsManager manager = new DBUserDetailsManager();
//        return manager;
//    }


}
