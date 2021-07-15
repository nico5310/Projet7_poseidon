package com.nnk.springboot.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BidListTest {

    @Test
    @DisplayName("Construtor BidList Test")
    public void testConstructor() {

        BidList actualBidList = new BidList("Account", "Type", 10.0);

        assertEquals("Account", actualBidList.getAccount());
        assertEquals("Type", actualBidList.getType());
        assertEquals(10.0, actualBidList.getBidQuantity().doubleValue());

    }
}

