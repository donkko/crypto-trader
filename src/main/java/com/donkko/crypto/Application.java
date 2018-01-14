package com.donkko.crypto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.donkko.crypto.exchange.korbit.api.KorbitApiSetting;

@EnableScheduling
@SpringBootApplication
public class Application {

    @Bean
    public KorbitApiSetting korbitApiSetting() {
        return new KorbitApiSetting(System.getProperty("korbit.key"),
                                    System.getProperty("korbit.secret"),
                                    System.getProperty("korbit.username"),
                                    System.getProperty("korbit.password"));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
