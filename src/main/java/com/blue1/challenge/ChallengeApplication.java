package com.blue1.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class}
)
public class ChallengeApplication {

  public static void main(String... args) {
    SpringApplication.run(ChallengeApplication.class, args);
  }
}

//TODO lombok
//TODO comm
