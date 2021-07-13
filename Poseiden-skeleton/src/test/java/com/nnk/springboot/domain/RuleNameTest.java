package com.nnk.springboot.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RuleNameTest {

    @Test
    @DisplayName("Constructor RuleName Test")
    public void constructorRuleNameTest() {

        RuleName actualRuleName = new RuleName("Name", "Description", "Json", "Template", "Sql Str", "Sql Part");

        assertNull(actualRuleName.getId());
        assertEquals("Name", actualRuleName.getName());
        assertEquals("Description", actualRuleName.getDescription());
        assertEquals("Json", actualRuleName.getJson());
        assertEquals("Template", actualRuleName.getTemplate());
        assertEquals("Sql Str", actualRuleName.getSqlStr());
        assertEquals("Sql Part", actualRuleName.getSqlPart());

    }
}

