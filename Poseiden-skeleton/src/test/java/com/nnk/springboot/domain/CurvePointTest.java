package com.nnk.springboot.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CurvePointTest {


    @Test
    @DisplayName("Constructor CurvePoint test")
    public void constructorCurvePointTest() {

        CurvePoint actualCurvePoint = new CurvePoint(1, 10.0, 10.0);

        assertNull(actualCurvePoint.getAsOfDate());
        assertEquals(1, actualCurvePoint.getCurveId().intValue());
        assertEquals(10.0, actualCurvePoint.getTerm().doubleValue());
        assertEquals(10.0, actualCurvePoint.getValue().doubleValue());
    }
}

