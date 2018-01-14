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
public class KorbitApiServiceTest {

    @Autowired KorbitApiService korbitApiService;
    @Autowired KorbitApiSetting korbitApiSetting;

    @Before
    public void setup() throws Exception {
        KorbitAccessToken korbitAccessToken = korbitApiService.issueAccessToken();
        korbitApiSetting.setAccessToken(korbitAccessToken);
    }

    @Test
    public void tokenOperation() throws Exception {
        KorbitAccessToken korbitAccessToken = korbitApiService.issueAccessToken();
        System.out.println(korbitAccessToken);

        korbitApiSetting.setAccessToken(korbitAccessToken);
        System.out.println(korbitApiService.refreshAccessToken());
    }

    @Test
    public void getTicker() throws Exception {
        System.out.println(korbitApiService.getTicker());
    }

    @Test
    public void marketBuy() throws Exception {
        korbitApiService.placeMarketBuy(5000);
    }

    @Test
    public void marketSell() throws Exception {
        korbitApiService.placeMarketSell(0.1);
    }
}
