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
@Table(name = "rating")
public class Rating implements Serializable {
    // TODO: Map columns in data table RATING with corresponding java fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotNull(message = "MoodysRating is mandatory") String moodysRating;

    @NotNull(message = "SandPRating is mandatory") String sandPRating;

    @NotNull(message = "FitchRating is mandatory") String fitchRating;

    @NotNull(message = "OrderNumber is mandatory") Integer orderNumber;


    public Rating(String moodysRating, String sandPRating, String fitchRating, Integer orderNumber) {

        this.moodysRating = moodysRating;
        this.sandPRating  = sandPRating;
        this.fitchRating  = fitchRating;
        this.orderNumber  = orderNumber;
    }

    public Rating() {

    }
}
