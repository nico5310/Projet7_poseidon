package com.nnk.springboot.integration;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Sql(executionPhase= Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts="classpath:/create_db_script-Test.sql")
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class RatingIT {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser
    @Test
    @DisplayName("Rating test")
    public void homeRatingAdminTest() throws Exception {

        mockMvc.perform(get("/rating/list"))
               .andExpect(status().isOk())
               .andExpect(view().name("/rating/list"));

    }

    @Test
    @DisplayName("Rating test")
    public void homeRatingAnonymousTest() throws Exception {

        mockMvc.perform(get("/rating/list"))
               .andExpect(status().isUnauthorized());

    }

    @WithMockUser
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

    @WithMockUser
    @Test
    @DisplayName("AddRatingForm")
    public void addRatingFormTest() throws Exception {

        mockMvc.perform(get("/rating/add"))
               .andExpect(status().isOk())
               .andExpect(view().name("/rating/add"));

    }

    @WithMockUser
    @Test
    @DisplayName("ValidateRatingList")
    public void validateRatingTest() throws Exception {

        Rating rating = new Rating();
        rating.setMoodysRating("moody");
        rating.setSandPRating("sand");
        rating.setFitchRating("fitch");
        rating.setOrderNumber(1);
        ratingRepository.save(rating);

        mockMvc.perform(post("/rating/list"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("rating", Matchers.hasSize(1)));

    }

    @WithMockUser
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

    @WithMockUser
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

    @WithMockUser
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
