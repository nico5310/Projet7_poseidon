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
@Table(name = "trade")
public class Trade implements Serializable {
    // TODO: Map columns in data table TRADE with corresponding java fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer tradeId ;

    @NotNull(message = "Account is mandatory")
    String account ;

    @NotNull(message = "Account is mandatory")
    String type ;

    Double buyQuantity ;
    Double sellQuantity ;
    Double buyPrice ;
    Double sellPrice ;
    String benchmark ;
    Timestamp tradeDate ;
    String security ;
    String status ;
    String trader ;
    String book ;
    String creationName ;
    Timestamp creationDate ;
    String revisionName ;
    Timestamp revisionDate ;
    String dealName ;
    String dealType ;
    String sourceListId ;
    String side ;

    public Trade(Integer tradeId, String account, String type, Double buyQuantity, Double sellQuantity, Double buyPrice, Double sellPrice, String benchmark, Timestamp tradeDate, String security, String status, String trader, String book, String creationName, Timestamp creationDate, String revisionName, Timestamp revisionDate, String dealName, String dealType, String sourceListId, String side) {

        this.tradeId      = tradeId;
        this.account      = account;
        this.type         = type;
        this.buyQuantity  = buyQuantity;
        this.sellQuantity = sellQuantity;
        this.buyPrice     = buyPrice;
        this.sellPrice    = sellPrice;
        this.benchmark    = benchmark;
        this.tradeDate    = tradeDate;
        this.security     = security;
        this.status       = status;
        this.trader       = trader;
        this.book         = book;
        this.creationName = creationName;
        this.creationDate = creationDate;
        this.revisionName = revisionName;
        this.revisionDate = revisionDate;
        this.dealName     = dealName;
        this.dealType     = dealType;
        this.sourceListId = sourceListId;
        this.side         = side;
    }

    public Trade() {

    }
}
