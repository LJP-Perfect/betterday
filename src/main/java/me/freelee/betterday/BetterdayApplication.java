package me.freelee.betterday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("me.freelee.betterday.dao")
@EnableWebSecurity
public class BetterdayApplication {

    public static void main(String[] args) {
        SpringApplication.run(BetterdayApplication.class, args);
    }


}
