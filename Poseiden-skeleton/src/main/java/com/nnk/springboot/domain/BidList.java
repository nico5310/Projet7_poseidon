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
@Table(name = "bidlist")
public class BidList implements Serializable {
    // TODO: Map columns in data table BIDLIST with corresponding java fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer bidListId ;

    @NotNull(message = "Account is mandatory")
    private String account ;

    @NotNull(message = "type is mandatory")
    private String type ;

    @NotNull(message = "bidQuantity is mandatory")
    private Double bidQuantity ;

    private Double askQuantity ;
    private Double bid ;
    private Double ask ;
    private String benchmark ;
    private Timestamp bidListDate ;
    private String commentary ;
    private String security ;
    private String status ;
    private String trader ;
    private String book ;
    private String creationName ;
    private Timestamp creationDate ;
    private String revisionName ;
    private Timestamp revisionDate ;
    private String dealName ;
    private String dealType ;
    private String sourceListId ;
    private String side ;

    public BidList(Integer bidListId, String account, String type, Double bidQuantity, Double askQuantity, Double bid, Double ask, String benchmark, Timestamp bidListDate, String commentary, String security, String status, String trader, String book, String creationName, Timestamp creationDate, String revisionName, Timestamp revisionDate, String dealName, String dealType, String sourceListId, String side) {

        this.bidListId    = bidListId;
        this.account      = account;
        this.type         = type;
        this.bidQuantity  = bidQuantity;
        this.askQuantity  = askQuantity;
        this.bid          = bid;
        this.ask          = ask;
        this.benchmark    = benchmark;
        this.bidListDate  = bidListDate;
        this.commentary   = commentary;
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

    public BidList() {

    }
}
