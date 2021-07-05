package com.nnk.springboot.integrations;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.RatingRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class RatingIT {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    RatingRepository ratingRepository;


    @Test
    @DisplayName("Rating test")
    public void homeRatingTest() throws Exception {

        mockMvc.perform(get("/rating/list"))
               .andExpect(status().isOk())
               .andExpect(view().name("/rating/list"));

    }

    @Test
    @DisplayName("RatingTests2")
    public void homeRatingTest2() throws Exception {

        Rating rating = new Rating();
        rating.setMoodysRating("moody");
        rating.setSandPRating("sand");
        rating.setFitchRating("fitch");
        rating.setOrderNumber(1);
        ratingRepository.save(rating);

        mockMvc.perform(get("/rating/list"))
               .andExpect(status().isOk())
               .andExpect(view().name("/rating/list"))
               .andExpect(model().attribute("rating", Matchers.hasSize(1)))
               .andReturn();

    }

    @Test
    @DisplayName("AddRatingForm")
    public void addRatingFormTest() throws Exception {

        mockMvc.perform(get("/rating/add"))
               .andExpect(status().isOk())
               .andExpect(view().name("/rating/add"));

    }

    @Test
    @DisplayName("ValidateRatingList")
    public void validateRatingTest() throws Exception {
        Rating rating = new Rating();
        rating.setMoodysRating("moody");
        rating.setSandPRating("sand");
        rating.setFitchRating("fitch");
        rating.setOrderNumber(1);
        ratingRepository.save(rating);

        mockMvc.perform(post("/rating/validate"))
               .andExpect(status().isOk());

    }

    @Test
    @DisplayName("ShowUpdateForm")
    public void showUpdateFormTest() throws Exception {
        Rating rating = new Rating();
        rating.setMoodysRating("moody");
        rating.setSandPRating("sand");
        rating.setFitchRating("fitch");
        rating.setOrderNumber(1);
        ratingRepository.save(rating);

        mockMvc.perform(get("/rating/update/1"))
               .andExpect(status().isOk());
    }

    @Test
    @DisplayName("UpdateRating")
    public void updateRatingTest() throws Exception {
        Rating rating = new Rating();
        rating.setMoodysRating("moody");
        rating.setSandPRating("sand");
        rating.setFitchRating("fitch");
        rating.setOrderNumber(1);
        ratingRepository.save(rating);
        mockMvc.perform(MockMvcRequestBuilders.post("/rating/update/1")
                                              .param("moodysRating", "moody")
                                              .param("sandPRating", "sand")
                                              .param("fitchRating", "fitch")
                                              .param("orderNumber", "2"))
               .andExpect(redirectedUrl("/rating/list"));
        mockMvc.perform(get("/rating/update/1"))
               .andExpect(status().isOk())
               .andExpect(model().attribute("rating", Matchers.hasProperty("orderNumber", is(2))));
    }

    @Test
    @DisplayName("deleteRating")
    public void deleteRatingTest() throws Exception {
        Rating rating = new Rating();
        rating.setMoodysRating("moody");
        rating.setSandPRating("sand");
        rating.setFitchRating("fitch");
        rating.setOrderNumber(1);
        ratingRepository.save(rating);

        mockMvc.perform(get("/rating/delete/1"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/rating/list"));

        mockMvc.perform(get("/rating/list"))
               .andExpect(status().isOk())
               .andExpect(model().attribute("rating", Matchers.hasSize(0)));
    }



}
