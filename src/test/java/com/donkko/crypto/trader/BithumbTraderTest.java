package com.donkko.crypto.trader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BithumbTraderTest {

    @Autowired BithumbTrader bithumbTrader;

    @Test
    public void sell() throws Exception {
        bithumbTrader.sell();
    }
}
