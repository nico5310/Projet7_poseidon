package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.validation.Valid;

/**
 * CurvePoint Service is CRUD methods for CurvePoint
 */
@Log4j2
@Service
public class CurvePointService {

    @Autowired
    CurvePointRepository curvePointRepository;

    /**
     * Show curvePoint List
     * @return all curvePoint
     */
    public String home (Model model) {
        log.info("Show curvePoint List");
        model.addAttribute("curvePoint", curvePointRepository.findAll());
        return "curvePoint/list";
    }

    /**
     * Add curvePoint
     */
    public void validate (@Valid CurvePoint curvePoint) {
        log.info("Add new curvePoint to curvePointList");
        curvePointRepository.save(curvePoint);

    }

    /**
     * Show update form
     */
    public void showUpdateForm(Integer id, Model model) {
        log.info("Find curvePoint by id ");
        CurvePoint curvePoint = curvePointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID:"+ id));
        model.addAttribute("curvePoint", curvePoint);
    }

    /**
     * Update curvePoint by id
     */
    public void updateCurvePoint(Integer id, @Valid CurvePoint curvePoint, Model model) {
        log.info("Update exist curvePoint by id" + id);
        curvePoint.setId(id);
        curvePointRepository.save(curvePoint);
        model.addAttribute("curvePoint",curvePointRepository.findAll());
    }

    /**
     * Delete curvePoint by id
     */
    public void deleteCurvePoint(Integer id, Model model) {
        log.info("Delete curvePoint by Id" + id);
        CurvePoint curvePoint = curvePointRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Invalid ID:" + id));
        curvePointRepository.delete(curvePoint);
        model.addAttribute("curvePoint", curvePointRepository.findAll());
    }
}
