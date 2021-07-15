package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
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

@ContextConfiguration(classes = {RuleNameService.class})
@ExtendWith(SpringExtension.class)
public class RuleNameServiceTest {

    @MockBean
    private RuleNameRepository ruleNameRepository;

    @Autowired
    private RuleNameService ruleNameService;

    @Test
    @DisplayName("Home RuleName test")
    public void homeRuleNameTest() {
        //GIVEN
        List<RuleName> ruleNameList = new ArrayList<>();
        //WHEN
        when(ruleNameRepository.findAll()).thenReturn(ruleNameList);
        //THEN
        assertEquals("ruleName/list", ruleNameService.home(new ConcurrentModel()));
        verify(ruleNameRepository).findAll();
    }

    @Test
    @DisplayName("Validate RuleName test")
    public void validateRuleNameTest() {
        //GIVEN
        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName("Name");
        ruleName.setDescription("Description");
        ruleName.setJson("Json");
        ruleName.setTemplate("Template");
        ruleName.setSqlStr("str");
        ruleName.setSqlPart("part");
        //WHEN
        when(ruleNameRepository.save(ruleName)).thenReturn(ruleName);
        ruleNameService.validate(ruleName);
        //THEN
        verify(ruleNameRepository).save(ruleName);
    }

    @Test
    @DisplayName("ShowUpdate RuleName test")
    public void showUpdateFormRuleNameTest() {
        //GIVEN
        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName("Name");
        ruleName.setDescription("Description");
        ruleName.setJson("Json");
        ruleName.setTemplate("Template");
        ruleName.setSqlStr("str");
        ruleName.setSqlPart("part");
        //WHEN
        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(ruleName));
        ruleNameService.showUpdateForm(1, new ConcurrentModel());
        //THEN
        verify(ruleNameRepository).findById(1);
    }

    @Test
    @DisplayName("update RuleName test")
    public void updateRuleNameTest() {
        //GIVEN
        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName("Name");
        ruleName.setDescription("Description");
        ruleName.setJson("Json");
        ruleName.setTemplate("Template");
        ruleName.setSqlStr("str");
        ruleName.setSqlPart("part");
        List<RuleName> ruleNameList = new ArrayList<>();
        when(ruleNameRepository.findAll()).thenReturn(ruleNameList);
        when(ruleNameRepository.save(ruleName)).thenReturn(ruleName);
        //GIVEN
        RuleName ruleName1 = new RuleName();
        ruleName1.setId(1);
        ruleName1.setName("Name");
        ruleName1.setDescription("Description");
        ruleName1.setJson("Json");
        ruleName1.setTemplate("Template");
        ruleName1.setSqlStr("str");
        ruleName1.setSqlPart("part2");
        ruleNameService.updateRuleName(1, ruleName1, new ConcurrentModel());
        //WHEN

        //THEN
        verify(ruleNameRepository).findAll();
        verify(ruleNameRepository).save(ruleName1);
        assertEquals(1, ruleName1.getId().intValue());
        assertEquals("part2", ruleName1.getSqlPart());
    }

    @Test
    public void testDeleteRuleName() {
        //GIVEN
        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName("Name");
        ruleName.setDescription("Description");
        ruleName.setJson("Json");
        ruleName.setTemplate("Template");
        ruleName.setSqlStr("str");
        ruleName.setSqlPart("part");
        //WHEN
        ruleNameRepository.deleteById(1);
        //THEN
        verify(ruleNameRepository).deleteById(1);
    }

}

