package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Log4j2
@Getter
@Setter
@Entity
@Table(name = "trade")
public class Trade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer tradeId;

    @NotNull(message = "Account is mandatory")
    @NotBlank(message = "template not be blank")
    String account;

    @NotNull(message = "Type is mandatory")
    @NotBlank(message = "template not be blank")
    String type;

    @NotNull(message = "buyQuantity is mandatory")
    Double buyQuantity;

    Double    sellQuantity;
    Double    buyPrice;
    Double    sellPrice;
    String    benchmark;
    Timestamp tradeDate;
    String    security;
    String    status;
    String    trader;
    String    book;
    String    creationName;
    Timestamp creationDate;
    String    revisionName;
    Timestamp revisionDate;
    String    dealName;
    String    dealType;
    String    sourceListId;
    String    side;

    public Trade(String account, String type, Double buyQuantity) {

        this.account     = account;
        this.type        = type;
        this.buyQuantity = buyQuantity;
    }

    public Trade() {

    }
}
