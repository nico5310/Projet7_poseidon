package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.RuleNameService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
import java.util.List;

/**
 * RuleName Controller is CRUD methods for RuleName
 */
@Log4j2
@Controller
public class RuleNameController {

    @Autowired
    private RuleNameService ruleNameService;

    @Autowired
    private RuleNameRepository ruleNameRepository;


    /**
     * Show ruleName HomePage
     * @return the list of ruleName List
     */
    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        log.info("Show RuleNameList page");
        ruleNameService.home(model);
        return "/ruleName/list";
    }

    /**
     * Add new ruleName form page
     * @return url Add new ruleName page
     */
    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        log.info("Get ruleName page formulaire");
        return "/ruleName/add";
    }

    /**
     * Validate Add new ruleName to ruleName list
     * @return url bidList page
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {

        if (result.hasErrors()) {
            log.error("ERROR, Add new ruleName isn't possible");
            return "/ruleName/add";
        }
        log.info("SUCCESS, Add new ruleName to ruleNameList");
        model.addAttribute("ruleName", ruleNameRepository.findAll());
        ruleNameService.validate(ruleName);
        return "redirect:/ruleName/list";
    }

    /**
     * Show Update ruleName form page
     * @return url ruleName Update page
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.info("Show Update form page by Id " + id);
        ruleNameService.showUpdateForm(id, model);
        return "/ruleName/update";
    }

    /**
     * Update ruleName by id
     * @return url ruleName List page
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.error("ERROR, Update ruleName isn't possible");
            return "redirect:/ruleName/update";
        }
        log.info("SUCCESS, Update ruleName by Id :"+ id);
        ruleNameService.updateRuleName(id, ruleName, model);
        return "redirect:/ruleName/list";
    }

    /**
     * Delete ruleName by id
     * @return url ruleName page
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        log.info("SUCCESS, Delete ruleName with Id :" + id);
        ruleNameService.deleteRuleName(id, model);
        return "redirect:/ruleName/list";
    }
}
