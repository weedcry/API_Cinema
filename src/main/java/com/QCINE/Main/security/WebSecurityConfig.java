package com.QCINE.Main.security;

import com.QCINE.Main.Service.User_Service;
import com.QCINE.Main.security.config.AuthEntryPointJwt;
import com.QCINE.Main.security.config.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    User_Service userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;


    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setPreAuthenticationChecks(toCheck -> {});

        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/ws/**").permitAll()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/user/resetpassword/**").permitAll()
                .antMatchers("/api/user/mail/**").permitAll()
                .antMatchers("/api/payment/**").permitAll()
                .antMatchers("/api/customer/comment/page").permitAll()
                .antMatchers("/api/customer/voucher/get").permitAll()
                .antMatchers("/api/customer/holiday/get").permitAll()
                .antMatchers("/api/customer/loaiphim/get").permitAll()
                .antMatchers("/api/customer/khuvuc/get").permitAll()
                .antMatchers("/api/customer/lich/**").permitAll()
                .antMatchers("/api/customer/phim/**").permitAll()
                .antMatchers("/api/customer/phong/**").permitAll()
                .antMatchers("/api/customer/rap/**").permitAll()

                .antMatchers(HttpMethod.GET,"/admin/khuvuc/**").hasAnyAuthority("STAFF","ADMIN","AUTHOR")
                .antMatchers(HttpMethod.GET,"/admin/rap/**").hasAnyAuthority("STAFF","ADMIN","AUTHOR")
                .antMatchers(HttpMethod.GET,"/admin/phim/**").hasAnyAuthority("STAFF","ADMIN","AUTHOR")
                .antMatchers(HttpMethod.GET,"/admin/phong/**").hasAnyAuthority("STAFF","ADMIN","AUTHOR")
                .antMatchers(HttpMethod.GET,"/admin/lich/**").hasAnyAuthority("STAFF","ADMIN","AUTHOR")
                .antMatchers(HttpMethod.GET,"/admin/cthd/**").hasAnyAuthority("STAFF","ADMIN","AUTHOR")
                .antMatchers(HttpMethod.GET,"/admin/voucher/**").hasAnyAuthority("STAFF","ADMIN","AUTHOR")
                .antMatchers(HttpMethod.GET,"/admin/week/**").hasAnyAuthority("STAFF","ADMIN","AUTHOR")
                .antMatchers(HttpMethod.GET,"/admin/loaiphim/**").hasAnyAuthority("STAFF","ADMIN","AUTHOR")
                .antMatchers(HttpMethod.GET,"/admin/holidays/**").hasAnyAuthority("STAFF","ADMIN","AUTHOR")
                .antMatchers(HttpMethod.GET,"/admin/hoadon/**").hasAnyAuthority("STAFF","ADMIN","AUTHOR")
                .antMatchers(HttpMethod.GET,"/admin/customer/**").hasAnyAuthority("STAFF","ADMIN","AUTHOR")

                .antMatchers(HttpMethod.POST,"/admin/khuvuc/**").hasAuthority("AUTHOR")
                .antMatchers(HttpMethod.PUT,"/admin/khuvuc/**").hasAuthority("AUTHOR")
                .antMatchers(HttpMethod.DELETE,"/admin/khuvuc/**").hasAuthority("AUTHOR")

                .antMatchers(HttpMethod.POST,"/admin/rap/**").hasAuthority("AUTHOR")
                .antMatchers(HttpMethod.PUT,"/admin/rap/**").hasAuthority("AUTHOR")
                .antMatchers(HttpMethod.DELETE,"/admin/rap/**").hasAuthority("AUTHOR")

                .antMatchers(HttpMethod.POST,"/admin/phim/**").hasAuthority("AUTHOR")
                .antMatchers(HttpMethod.PUT,"/admin/phim/**").hasAuthority("AUTHOR")
                .antMatchers(HttpMethod.DELETE,"/admin/phim/**").hasAuthority("AUTHOR")

                .antMatchers(HttpMethod.POST,"/admin/voucher/**").hasAuthority("AUTHOR")
                .antMatchers(HttpMethod.PUT,"/admin/voucher/**").hasAuthority("AUTHOR")
                .antMatchers(HttpMethod.DELETE,"/admin/voucher/**").hasAuthority("AUTHOR")

                .antMatchers(HttpMethod.POST,"/admin/week/**").hasAuthority("AUTHOR")
                .antMatchers(HttpMethod.PUT,"/admin/week/**").hasAuthority("AUTHOR")
                .antMatchers(HttpMethod.DELETE,"/admin/week/**").hasAuthority("AUTHOR")

                .antMatchers(HttpMethod.POST,"/admin/loaiphim/**").hasAuthority("AUTHOR")
                .antMatchers(HttpMethod.PUT,"/admin/loaiphim/**").hasAuthority("AUTHOR")
                .antMatchers(HttpMethod.DELETE,"/admin/loaiphim/**").hasAuthority("AUTHOR")

                .antMatchers(HttpMethod.POST,"/admin/image/**").hasAuthority("AUTHOR")
                .antMatchers(HttpMethod.DELETE,"/admin/image/**").hasAuthority("AUTHOR")

                .antMatchers(HttpMethod.POST,"/admin/holidays/**").hasAuthority("AUTHOR")
                .antMatchers(HttpMethod.PUT,"/admin/holidays/**").hasAuthority("AUTHOR")
                .antMatchers(HttpMethod.DELETE,"/admin/holidays/**").hasAuthority("AUTHOR")

                .antMatchers(HttpMethod.POST,"/admin/hoadon/**").hasAuthority("AUTHOR")
                .antMatchers(HttpMethod.PUT,"/admin/hoadon/**").hasAuthority("AUTHOR")
                .antMatchers(HttpMethod.DELETE,"/admin/hoadon/**").hasAuthority("AUTHOR")

                .antMatchers(HttpMethod.POST,"/admin/customer/**").hasAuthority("AUTHOR")
                .antMatchers(HttpMethod.PUT,"/admin/customer/**").hasAuthority("AUTHOR")
                .antMatchers(HttpMethod.DELETE,"/admin/customer/**").hasAuthority("AUTHOR")

                .antMatchers(HttpMethod.POST,"/admin/lich/**").hasAnyAuthority("AUTHOR","ADMIN")
                .antMatchers(HttpMethod.PUT,"/admin/lich/**").hasAnyAuthority("AUTHOR","ADMIN")
                .antMatchers(HttpMethod.DELETE,"/admin/lich/**").hasAnyAuthority("AUTHOR","ADMIN")

                .antMatchers(HttpMethod.POST,"/admin/phong/**").hasAnyAuthority("AUTHOR","ADMIN")
                .antMatchers(HttpMethod.PUT,"/admin/phong/**").hasAnyAuthority("AUTHOR","ADMIN")
                .antMatchers(HttpMethod.DELETE,"/admin/phong/**").hasAnyAuthority("AUTHOR","ADMIN")

                .antMatchers(HttpMethod.POST,"/admin/holidays/**").hasAnyAuthority("AUTHOR","ADMIN")
                .antMatchers(HttpMethod.PUT,"/admin/holidays/**").hasAnyAuthority("AUTHOR","ADMIN")
                .antMatchers(HttpMethod.DELETE,"/admin/holidays/**").hasAnyAuthority("AUTHOR","ADMIN")

                .antMatchers(HttpMethod.POST,"/admin/week/**").hasAnyAuthority("AUTHOR","ADMIN")
                .antMatchers(HttpMethod.GET,"/admin/week/**").hasAnyAuthority("AUTHOR","ADMIN")

                .antMatchers("/admin/**").hasAnyAuthority("AUTHOR","ADMIN")
                .anyRequest().authenticated();
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}