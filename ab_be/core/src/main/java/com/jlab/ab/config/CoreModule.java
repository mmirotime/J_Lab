package com.jlab.ab.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.jlab.ab")
@EntityScan("com.jlab.ab")
@EnableJpaRepositories("com.jlab.ab.repository")
public class CoreModule {
}
