package com.example.board.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages= {"com.example.board"})
@EnableJpaRepositories(basePackages = "com.example.board")
public class JPAConfiguration {

}
