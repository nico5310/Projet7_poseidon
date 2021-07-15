package com.nnk.springboot.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
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

