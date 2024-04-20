package com.hamza.taskgenie.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Data
@Entity
@Table(name = "reviews")
public class Review {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long reviewerId;

    Long revieweeId;

    Float rating;

    String comment;

    Date timestamp;

}
