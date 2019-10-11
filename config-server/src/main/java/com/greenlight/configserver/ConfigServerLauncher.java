package com.greenlight.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConfigServerLauncher {
  public static void main(String[] args) {
    SpringApplication.run(ConfigServerLauncher.class, args);
  }

  public void s() {
    if (true) {
      System.out.println("test SQ analytics");
    }
  }
}
