package com.nnk.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ConcurrentModel;

@ContextConfiguration(classes = {CurvePointService.class})
@ExtendWith(SpringExtension.class)
public class CurvePointServiceTest {

    @MockBean
    private CurvePointRepository curvePointRepository;

    @Autowired
    private CurvePointService curvePointService;

    @Test
    @DisplayName("HomeCurvePoint test")
    public void HomeCurvePointTest() {

        when(curvePointRepository.findAll()).thenReturn(new ArrayList<CurvePoint>());
        assertEquals("curvePoint/list", curvePointService.home(new ConcurrentModel()));
        verify(curvePointRepository).findAll();
    }

    @Test
    @DisplayName("Validate BidList test")
    public void validateCurvePointTest() {
        //GIVEN
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(1);
        curvePoint.setTerm(10.0);
        curvePoint.setValue(10.0);
        //WHEN
        curvePointService.validate(curvePoint, new ConcurrentModel());
        //THEN
        verify(curvePointRepository).findAll();
        verify(curvePointRepository).save(curvePoint);
    }


    @Test
    @DisplayName("ShowUpdate CurvePoint test")
    public void showUpdateFormCurvePointTest() {
        //GIVEN
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(1);
        curvePoint.setTerm(10.0);
        curvePoint.setValue(10.0);
        //WHEN
        Optional<CurvePoint> ofResult = Optional.<CurvePoint>of(curvePoint);
        when(curvePointRepository.findById((Integer) any())).thenReturn(ofResult);
        //THEN
        curvePointService.showUpdateForm(1, new ConcurrentModel());
        verify(curvePointRepository).findById(1);
    }

    @Test
    @DisplayName("updateCurve test")
    public void updateCurvePointTest() {
        //GIVEN
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(1);
        curvePoint.setTerm(10.0);
        curvePoint.setValue(10.0);
        when(curvePointRepository.findAll()).thenReturn(new ArrayList<CurvePoint>());
        when(curvePointRepository.save((CurvePoint) any())).thenReturn(curvePoint);
        CurvePoint curvePoint1 = new CurvePoint();
        curvePoint1.setId(1);
        curvePoint1.setCurveId(1);
        curvePoint1.setTerm(10.0);
        curvePoint1.setValue(20.0);
        curvePointService.updateCurvePoint(1, curvePoint1, new ConcurrentModel());
        //WHEN

        //THEN
        verify(curvePointRepository).findAll();
        verify(curvePointRepository).save(curvePoint1);
        assertEquals(1, curvePoint1.getCurveId().intValue());
        assertEquals(20.0, curvePoint1.getValue().intValue());

    }

    @Test
    public void testDeleteCurvePoint() {
        //GIVEN
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(1);
        curvePoint.setTerm(10.0);
        curvePoint.setValue(10.0);
        //WHEN
        curvePointRepository.deleteById(1);
        //THEN
        verify(this.curvePointRepository).deleteById(1);

    }

    @Test
    public void testDeleteCurvePoint2() {

        when(this.curvePointRepository.findAll()).thenReturn(new ArrayList<CurvePoint>());
        doNothing().when(this.curvePointRepository).delete((CurvePoint) any());
        when(this.curvePointRepository.findById((Integer) any())).thenReturn(Optional.<CurvePoint>empty());
        assertThrows(IllegalArgumentException.class, () -> this.curvePointService.deleteCurvePoint(1, new ConcurrentModel()));
        verify(this.curvePointRepository).findById((Integer) any());
    }
}

