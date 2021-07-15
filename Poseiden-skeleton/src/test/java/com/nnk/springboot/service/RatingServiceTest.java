package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ConcurrentModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {RatingService.class})
@ExtendWith(SpringExtension.class)
public class RatingServiceTest {

    @MockBean
    private RatingRepository ratingRepository;

    @Autowired
    private RatingService ratingService;

    @Test
    @DisplayName("Home RatingList test")
    public void homeRatingTest() {
        //GIVEN
        List<Rating> ratingList = new ArrayList<>();
        //WHEN
        when(ratingRepository.findAll()).thenReturn(ratingList);
        //THEN
        assertEquals("rating/list", ratingService.home(new ConcurrentModel()));
        verify(ratingRepository).findAll();
    }

    @Test
    @DisplayName("Validate Rating test")
    public void validateRatingTest() {
        //GIVEN
        Rating rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating("Moodys Rating");
        rating.setSandPRating("Sand PRating");
        rating.setFitchRating("Fitch Rating");
        rating.setOrderNumber(10);
        //WHEN
        when(ratingRepository.save(rating)).thenReturn(rating);
        ratingService.validate(rating);
        //THEN
        verify(ratingRepository).save(rating);
    }

    @Test
    @DisplayName("ShowUpdate Rating test")
    public void showUpdateFormRatingTest() {
        //GIVEN
        Rating rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating("Moodys Rating");
        rating.setSandPRating("Sand PRating");
        rating.setFitchRating("Fitch Rating");
        rating.setOrderNumber(10);
        //WHEN
        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));
        ratingService.showUpdateForm(1, new ConcurrentModel());
        //THEN
        verify(ratingRepository).findById(1);
    }

    @Test
    @DisplayName("update Rating test")
    public void updateRatingTest() {
        //GIVEN
        Rating rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating("Moodys Rating");
        rating.setSandPRating("Sand PRating");
        rating.setFitchRating("Fitch Rating");
        rating.setOrderNumber(10);
        List<Rating> ratingList = new ArrayList<>();
        when(ratingRepository.findAll()).thenReturn(ratingList);
        when(this.ratingRepository.save(rating)).thenReturn(rating);
        Rating rating1 = new Rating();
        rating1.setId(1);
        rating1.setMoodysRating("Moodys Rating");
        rating1.setSandPRating("Sand PRating");
        rating1.setFitchRating("Fitch Rating");
        rating1.setOrderNumber(20);
        ratingService.updateRating(1, rating1, new ConcurrentModel());
        //WHEN

        //THEN
        verify(ratingRepository).findAll();
        verify(ratingRepository).save(rating1);
        assertEquals(1, rating1.getId().intValue());
        assertEquals(20, rating1.getOrderNumber().intValue());
    }

    @Test
    @DisplayName("Delete Rating test")
    public void deleteRatingTest() {
        //GIVEN
        Rating rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating("Moodys Rating");
        rating.setSandPRating("Sand PRating");
        rating.setFitchRating("Fitch Rating");
        rating.setOrderNumber(10);
        //WHEN
        ratingRepository.deleteById(1);
        //THEN
        verify(ratingRepository).deleteById(1);

    }

}

