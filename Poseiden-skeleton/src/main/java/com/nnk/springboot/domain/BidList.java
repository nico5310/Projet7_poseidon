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

    public BidList(String account, String type, Double bidQuantity) {

        this.account     = account;
        this.type        = type;
        this.bidQuantity = bidQuantity;
    }
    public BidList() {

    }

    public BidList(String account_test, String type_test, double v) {}
}
