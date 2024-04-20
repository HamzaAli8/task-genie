package com.hamza.taskgenie.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "bids")
public class Bid {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long taskId;

    Long userId;

    BigDecimal amount;

    Date timeStamp;
}
