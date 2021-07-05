package com.nnk.springboot;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleTests {

	@Autowired
	private RuleNameRepository ruleNameRepository;

	@Test
	public void ruleTest() {
		RuleName ruleName = new RuleName();
		ruleName.setName("rule");
		ruleName.setDescription("description");
		ruleName.setJson("json");
		ruleName.setTemplate("template");
		ruleName.setSqlStr("str");
		ruleName.setSqlPart("part");
		ruleNameRepository.save(ruleName);

		// Save
		ruleName = ruleNameRepository.save(ruleName);
		Assert.assertNotNull(ruleName.getId());
		Assert.assertTrue(ruleName.getName().equals("rule"));

		// Update
		ruleName.setName("Rule Name Update");
		ruleName = ruleNameRepository.save(ruleName);
		Assert.assertTrue(ruleName.getName().equals("Rule Name Update"));

		// Find
		List<RuleName> listResult = ruleNameRepository.findAll();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = ruleName.getId();
		ruleNameRepository.delete(ruleName);
		Optional<RuleName> ruleList = ruleNameRepository.findById(id);
		Assert.assertFalse(ruleList.isPresent());
	}
}
