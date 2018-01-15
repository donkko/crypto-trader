package com.donkko.crypto.exchange.korbit.api;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class KorbitApiConfig {

    @Bean
    public KorbitApi kotbitApi() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient client = builder
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                               .newBuilder()
                                               .addHeader("User-Agent", "curl/7.54.0")
                                               .build();
                        return chain.proceed(request);
                    }
                })
                //.addInterceptor(interceptor)
                .build();

        return new Retrofit.Builder()
                .baseUrl("https://api.korbit.co.kr")
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create(createObjectMapper()))
                .build()
                .create(KorbitApi.class);
    }

    private static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }
}
