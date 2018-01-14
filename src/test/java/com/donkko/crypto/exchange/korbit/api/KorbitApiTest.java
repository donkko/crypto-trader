package com.donkko.crypto.exchange.korbit.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.donkko.crypto.exchange.korbit.api.response.KorbitAccessToken;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KorbitApiTest {

    @Autowired KorbitApiService korbitApiService;
    @Autowired KorbitApiSetting korbitApiSetting;
    @Autowired KorbitApi korbitApi;

    @Before
    public void setup() throws Exception {
        KorbitAccessToken korbitAccessToken = korbitApiService.issueAccessToken();
        korbitApiSetting.setAccessToken(korbitAccessToken);
    }

    @Test
    public void ticker() throws Exception {
        System.out.println(korbitApi.getTicker("eth_krw").execute().body());
    }

    @Test
    public void myBalances() throws Exception {
        System.out.println(
                korbitApi.getMyBalances(korbitApiSetting.getAccessToken().getBearerWithAccessToken()).execute().body());
    }
}
