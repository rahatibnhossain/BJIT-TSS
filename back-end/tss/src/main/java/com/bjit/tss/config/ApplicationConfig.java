package com.bjit.tss.config;

import com.bjit.tss.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final LoginRepository loginRepository;

    @Bean
    public UserDetailsService userDetailsService(){
         return new UserDetailsService() {
             @Override
             public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                 return loginRepository.findByEmail(username)
                         .orElseThrow(()-> new UsernameNotFoundException("User not found"));
             }
         };
    }

}
