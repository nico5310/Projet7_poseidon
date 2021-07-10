package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Log4j2
@Getter
@Setter
@Entity
@Table(name = "rulename")
public class RuleName implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id ;

    @NotNull(message = "Name not be null")
    String name ;

    @NotNull(message = "Description not be null")
    String description ;

    @NotNull(message = "Json not be null")
    String json ;

    @NotNull(message = "template not be null")
    String template ;

    @NotNull(message = "sqlStr not be null")
    String sqlStr ;

    @NotNull(message = "sqlPart not be null")
    String sqlPart ;

    public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {

        this.name        = name;
        this.description = description;
        this.json        = json;
        this.template    = template;
        this.sqlStr      = sqlStr;
        this.sqlPart     = sqlPart;
    }

    public RuleName() {

    }
}
