package com.egg.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import com.egg.servicios.UsuarioServicio;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SeguridadWeb {

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
  return http 
               .httpBasic()
               .and().authorizeHttpRequests()
               .anyRequest().permitAll()
               .and().build();
}


/*   @Bean
  public UserDetailsService userDetails(){
      var user = User.withUsername("lucas")
      .password("pass123")
      .roles("ADMIN")
      .build();
      return new InMemoryUserDetailsManager(user);
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return NoOpPasswordEncoder.getInstance();
  }
  */

@Autowired
public UsuarioServicio usuarioServicio;

@Autowired
public void configureGlobal(AuthenticationManagerBuilder auht) throws Exception{
auht.userDetailsService(usuarioServicio)
.passwordEncoder(new BCryptPasswordEncoder());
}



    /*   protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests()    
                .requestMatchers("/**")
                .permitAll();
    }*/ 
}
