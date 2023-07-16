package com.bjit.tss;

import com.bjit.tss.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class TssApplication implements CommandLineRunner {

    private final RegisterService registerService;

    public static void main(String[] args) {
        SpringApplication.run(TssApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        registerService.adminRegistration("rahatibnhossain@gmail.com", "Rahat1234");
    }
}