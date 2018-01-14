package com.donkko.crypto.exchange.bithumb.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.donkko.crypto.exchange.bithumb.api.BithumbPrivateApi;
import com.donkko.crypto.model.Currency;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BithumbPrivateApiTest {

    @Autowired
    BithumbPrivateApi bithumbPrivateApi;

    @Test
    public void test() throws Exception {
        //System.out.println(bithumbPrivateApi.placeMarketBuy(Currency.ETH, 0.01));
        //System.out.println(bithumbPrivateApi.placeMarketSell(Currency.ETH, 0.0100)); // 네자리수로 해야함
        System.out.println(bithumbPrivateApi.getMyBalance(Currency.ETH).getData().get("available_eth"));
    }

    @Test
    public void unicode2utf() throws Exception {
        String string = "\uc7a0\uc2dc \ud6c4 \uc774\uc6a9\ud574 \uc8fc\uc2ed\uc2dc\uc624.9999";
        byte[] utf8 = string.getBytes("UTF-8");

        // Convert from UTF-8 to Unicode
        string = new String(utf8, "UTF-8");
        System.out.println(string);

    }
}
