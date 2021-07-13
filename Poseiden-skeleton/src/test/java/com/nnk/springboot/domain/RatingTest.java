package com.nnk.springboot.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RatingTest {

    @Test
    @DisplayName("Constructor Rating Test")
    public void constructorRatingTest() {

        Rating actualRating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);

        assertNull(actualRating.getId());
        assertEquals("Moodys Rating", actualRating.getMoodysRating());
        assertEquals("Sand PRating", actualRating.getSandPRating());
        assertEquals("Fitch Rating", actualRating.getFitchRating());
        assertEquals(10, actualRating.getOrderNumber().intValue());

    }
}

