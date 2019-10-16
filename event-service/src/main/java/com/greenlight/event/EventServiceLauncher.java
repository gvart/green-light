package com.greenlight.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableR2dbcRepositories
@EnableTransactionManagement
@SpringBootApplication
public class EventServiceLauncher {
  public static void main(String[] args) {
    SpringApplication.run(EventServiceLauncher.class, args);
  }
}

