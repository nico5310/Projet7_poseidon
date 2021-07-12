package com.nnk.springboot.integrations;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
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
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class RuleNameIT {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    RuleNameRepository ruleNameRepository;

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
    @DisplayName("Rule test")
    public void homeRuleAdminTest() throws Exception {

        mockMvc.perform(get("/ruleName/list"))
               .andExpect(status().isOk())
               .andExpect(view().name("/ruleName/list"));

    }

    @Test
    @DisplayName("Rule test")
    public void homeRuleAnonymousTest() throws Exception {

        mockMvc.perform(get("/ruleName/list"))
               .andExpect(status().isUnauthorized());

    }

    @WithMockUser
    @Test
    @DisplayName("RuleTests2")
    public void homeRuleNameTest2() throws Exception {

        RuleName ruleName = new RuleName();
        ruleName.setName("rule");
        ruleName.setDescription("description");
        ruleName.setJson("json");
        ruleName.setTemplate("template");
        ruleName.setSqlStr("str");
        ruleName.setSqlPart("part");
        ruleNameRepository.save(ruleName);

        mockMvc.perform(get("/ruleName/list"))
               .andExpect(status().isOk())
               .andExpect(view().name("/ruleName/list"))
               .andExpect(model().attribute("ruleName", Matchers.hasSize(1)))
               .andReturn();

    }

    @WithMockUser
    @Test
    @DisplayName("AddRuleNameForm")
    public void addRuleNameFormTest() throws Exception {

        mockMvc.perform(get("/ruleName/add"))
               .andExpect(status().isOk())
               .andExpect(view().name("/ruleName/add"));

    }

    @WithMockUser
    @Test
    @DisplayName("ValidateRuleName")
    public void validateRuleNameTest() throws Exception {

        RuleName ruleName = new RuleName();
        ruleName.setName("rule");
        ruleName.setDescription("description");
        ruleName.setJson("json");
        ruleName.setTemplate("template");
        ruleName.setSqlStr("str");
        ruleName.setSqlPart("part");
        ruleNameRepository.save(ruleName);

        mockMvc.perform(get("/ruleName/list"))
               .andExpect(status().isOk())
               .andExpect(model().attribute("ruleName", Matchers.hasSize(1)));;

    }

    @WithMockUser
    @Test
    @DisplayName("ShowUpdateForm")
    public void showUpdateFormTest() throws Exception {
        RuleName ruleName = new RuleName();
        ruleName.setName("rule");
        ruleName.setDescription("description");
        ruleName.setJson("json");
        ruleName.setTemplate("template");
        ruleName.setSqlStr("str");
        ruleName.setSqlPart("part");
        ruleNameRepository.save(ruleName);

        mockMvc.perform(get("/ruleName/update/1"))
               .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    @DisplayName("UpdateRuleName")
    public void updateRuleNameTest() throws Exception {

        RuleName ruleName = new RuleName();
        ruleName.setName("rule");
        ruleName.setDescription("description");
        ruleName.setJson("json");
        ruleName.setTemplate("template");
        ruleName.setSqlStr("str");
        ruleName.setSqlPart("part");
        ruleNameRepository.save(ruleName);

        mockMvc.perform(MockMvcRequestBuilders.post("/ruleName/update/1")
              .param("name", "ruleName")
              .param("description", "description")
              .param("json", "json")
              .param("template", "template")
              .param("sqlStr", "str")
              .param("sqlPart", "part"))
              .andExpect(redirectedUrl("/ruleName/list"));

        mockMvc.perform(get("/ruleName/update/1"))
               .andExpect(status().isOk())
               .andExpect(model().attribute("ruleName", Matchers.hasProperty("name", is("ruleName"))));
    }

    @WithMockUser
    @Test
    @DisplayName("deleteRating")
    public void deleteRatingTest() throws Exception {
        RuleName ruleName = new RuleName();
        ruleName.setName("rule");
        ruleName.setDescription("description");
        ruleName.setJson("json");
        ruleName.setTemplate("template");
        ruleName.setSqlStr("str");
        ruleName.setSqlPart("part");
        ruleNameRepository.save(ruleName);

        mockMvc.perform(get("/ruleName/delete/1"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/ruleName/list"));

        mockMvc.perform(get("/ruleName/list"))
               .andExpect(status().isOk())
               .andExpect(model().attribute("ruleName", Matchers.hasSize(0)));
    }

}
