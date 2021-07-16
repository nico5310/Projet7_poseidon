package com.nnk.springboot.integration;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Sql(executionPhase= Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts="classpath:/create_db_script-Test.sql")
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class CurvePointIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    CurvePointRepository curvePointRepository;

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
    @DisplayName("homeCurvePoint test")
    public void homeCurvePointAdminTest() throws Exception {

        mockMvc.perform(get("/curvePoint/list"))
               .andExpect(status().isOk())
               .andExpect(view().name("curvePoint/list"));

    }


    @Test
    @DisplayName("homeCurvePoint test")
    public void homeCurvePointAnonymousTest() throws Exception {

        mockMvc.perform(get("/curvePoint/list"))
               .andExpect(status().isUnauthorized());

    }

    @WithMockUser
    @Test
    @DisplayName("homeCurvePoint test2")
    public void homeCurvePointTest2() throws Exception {

        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(10.00);
        curvePoint.setValue(10.00);
        curvePointRepository.save(curvePoint);

        mockMvc.perform(get("/curvePoint/list"))
               .andExpect(status().isOk())
               .andExpect(view().name("curvePoint/list"))
               .andExpect(model().attribute("curvePoint", Matchers.hasSize(1)))
               .andReturn();

    }

    @WithMockUser
    @Test
    @DisplayName("AddCurvePointForm")
    public void addCurvePointFormTest() throws Exception {

        mockMvc.perform(get("/curvePoint/add"))
               .andExpect(status().isOk())
               .andExpect(view().name("/curvePoint/add"));

    }

    @WithMockUser
    @Test
    @DisplayName("ValidateCurvePointList")
    public void validateCurvePointTest() throws Exception {

        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(10.00);
        curvePoint.setValue(10.00);
        curvePointRepository.save(curvePoint);

        mockMvc.perform(get("/curvePoint/list"))
               .andExpect(status().isOk())
               .andExpect(model().attribute("curvePoint", Matchers.hasSize(1)));

    }

    @WithMockUser
    @Test
    @DisplayName("ShowUpdateForm")
    public void showUpdateFormTest() throws Exception {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(10.00);
        curvePoint.setValue(10.00);
        curvePointRepository.save(curvePoint);

        mockMvc.perform(get("/curvePoint/update/1"))
               .andExpect(status().isOk());

        curvePointRepository.delete(curvePointRepository.findAll().get(0));
    }

    @WithMockUser
    @Test
    @DisplayName("UpdateCurvePoint")
    public void updateCurvePointTest() throws Exception {

        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(10.00);
        curvePoint.setValue(10.00);
        curvePointRepository.save(curvePoint);

        mockMvc.perform(MockMvcRequestBuilders.post("/curvePoint/update/1")
               .param("curveId", "1")
               .param("term", "20.00")
               .param("value", "10.00"))
               .andExpect(redirectedUrl("/curvePoint/list"));
        mockMvc.perform(get("/curvePoint/update/1"))
               .andExpect(status().isOk())
               .andExpect(model().attribute("curvePoint", Matchers.hasProperty("term", is(20.00))));

    }

    @WithMockUser
    @Test
    @DisplayName("deleteBid")
    public void deleteBidTest() throws Exception {

        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(10.00);
        curvePoint.setValue(10.00);
        curvePointRepository.save(curvePoint);

        mockMvc.perform(get("/curvePoint/delete/1"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/curvePoint/list"));

        mockMvc.perform(get("/curvePoint/list"))
               .andExpect(status().isOk())
               .andExpect(model().attribute("curvePoint", Matchers.hasSize(0)));

    }

}
