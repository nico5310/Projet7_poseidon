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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "CurveId must not be null")
    Integer curveId;

        @NotNull(message = "Term must not be null")
    Double term;

    @NotNull(message = "Value must not be null")
    Double value;


    Timestamp asOfDate;
    Timestamp creationDate;



    public CurvePoint(Integer curveId, Double term, Double value) {

        this.curveId = curveId;
        this.term    = term;
        this.value   = value;
    }

    public CurvePoint() {

    }


}
