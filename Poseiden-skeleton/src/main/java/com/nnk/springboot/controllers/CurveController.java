package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
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

@Log4j2
@Controller
public class CurveController {
    @Autowired
    CurvePointService curvePointService;

    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        log.info("Get CurvePoints List");
        curvePointService.home(model);
        return "curvePoint/list";
    }

    // ADD PAGE FORM
    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(CurvePoint curvePoint) {
        log.info("Add CurvePoint page formulaire");
        return "/curvePoint/add";
    }

    //SAVE NEW CURVEPOINT
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {

        if (result.hasErrors()) {
            log.error("ERROR, Add new curvepoint isn't possible");
            return "curvePoint/add";
        }
        log.info("SUCCESS, Add new curvepoint to curvePointList");
        curvePointService.validate(curvePoint, model);
        return "redirect:/curvePoint/list";
    }

    // UPDATE PAGE FORM
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.info("Show Update form page By Id" + id);
        curvePointService.showUpdateForm(id, model);
        return "curvePoint/update";
    }

    // UPDATE EXIST CURVEPOINT
    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.error("ERROR, Update curvePoint isn't possible");
            return "redirect:/curvePoint/update";
        }
        log.info("SUCCESS, Update curvePoint by Id :"+ curvePoint);
        curvePointService.updateCurvePoint(id, curvePoint, model);
        return "redirect:/curvePoint/list";
    }


    //DELETE CURVEPOINT
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") Integer id, Model model) {
        log.info("SUCCESS, Delete CURVEPOINT with Id :" + id);
        curvePointService.deleteCurvePoint(id, model);
        return "redirect:/curvePoint/list";
    }
}
