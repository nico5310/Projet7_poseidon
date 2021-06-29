package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Log4j2
@Getter
@Setter
@Entity
@Table(name = "curvepoint")
public class CurvePoint implements Serializable {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "CurveId must not be null")
    Integer curveId ;

    Timestamp asOfDate ;

    @NotNull(message = "Term must not be null")
    Double term ;

    @NotNull(message = "Value must not be null")
    Double value ;

    Timestamp creationDate ;


    public CurvePoint(Integer id, Integer curveId, Timestamp asOfDate, Double term, Double value, Timestamp creationDate) {

        this.id           = id;
        this.curveId      = curveId;
        this.asOfDate     = asOfDate;
        this.term         = term;
        this.value        = value;
        this.creationDate = creationDate;
    }

    public CurvePoint() {

    }


}
