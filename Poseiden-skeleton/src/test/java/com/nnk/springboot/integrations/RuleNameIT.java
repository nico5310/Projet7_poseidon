package com.nnk.springboot.integrations;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class RuleNameIT {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    RuleNameRepository ruleNameRepository;

    @WithMockUser
    @Test
    @DisplayName("Rule test")
    public void homeRuleTest() throws Exception {

        mockMvc.perform(get("/ruleName/list"))
               .andExpect(status().isOk())
               .andExpect(view().name("/ruleName/list"));

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

        mockMvc.perform(post("/ruleName/validate"))
               .andExpect(status().isOk());

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
                                              .param("strSql", "str")
                                              .param("strPart", "part"))
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
