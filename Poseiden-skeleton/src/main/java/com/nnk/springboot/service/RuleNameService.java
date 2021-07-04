package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.validation.Valid;

@Log4j2
@Service
public class RuleNameService {

    @Autowired
    RuleNameRepository ruleNameRepository;

    public String home (Model model) {
        log.info("Show bid list");
        model.addAttribute("bidList", ruleNameRepository.findAll());
        return "bidList/list";
    }

    public void validate (@Valid RuleName ruleName, Model model) {
        log.info("Add new ruleName to ruleNameList");
        ruleNameRepository.save(ruleName);
        model.addAttribute("ruleName", ruleNameRepository.findAll());
    }

    public void showUpdateForm(Integer id, Model model) {
        log.info("Find ruleName by id to ruleNameList");
        RuleName ruleName = ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID:" + id));
        model.addAttribute("ruleName", ruleName);
    }

    public void updateRuleName(Integer id, RuleName ruleName, Model model) {
        log.info("Update exist RuleName by id" + id);
        ruleName.setId(id);
        ruleNameRepository.save(ruleName);
        model.addAttribute("ruleName",ruleNameRepository.findAll());
    }

    public void deleteRuleName(Integer id, Model model) {
        log.info("Delete ruleName by Id" + id);
        RuleName ruleName = ruleNameRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Invalid ID:" + id));
        ruleNameRepository.delete(ruleName);
        model.addAttribute("ruleName", ruleNameRepository.findAll());
    }


}