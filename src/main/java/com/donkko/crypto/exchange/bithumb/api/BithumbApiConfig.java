package com.donkko.crypto.exchange.bithumb.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.donkko.crypto.exchange.bithumb.api.example.Api_Client;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class BithumbApiConfig {

    @Bean
    public BithumbPublicApi bithumbPublicApi() {
        return new Retrofit.Builder()
                .baseUrl("https://api.bithumb.com")
                .addConverterFactory(JacksonConverterFactory.create(createObjectMapper()))
                .build()
                .create(BithumbPublicApi.class);
    }

    private static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Bean
    public Api_Client apiClient() {
        return new Api_Client("", "");
    }
}
