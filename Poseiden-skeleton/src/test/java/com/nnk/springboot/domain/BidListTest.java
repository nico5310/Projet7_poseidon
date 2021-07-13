package com.nnk.springboot.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

