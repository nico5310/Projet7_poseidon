package com.nnk.springboot.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TradeTest {

    @Test
    @DisplayName("Constructor Trade Test")
    public void constructorTradeTest() {

        Trade actualTrade = new Trade("Account", "Type", 10.0);

        assertNull(actualTrade.getTradeId());
        assertEquals("Account", actualTrade.getAccount());
        assertEquals("Type", actualTrade.getType());
        assertEquals(10.0, actualTrade.getBuyQuantity().doubleValue());

    }
}

