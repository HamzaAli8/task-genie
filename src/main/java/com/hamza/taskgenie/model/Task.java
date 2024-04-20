package com.hamza.taskgenie.model;

import com.hamza.taskgenie.enumerations.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    String description;

    String location;

    Float budget;

    Date deadline;


    @Enumerated(EnumType.STRING)
    Status status;
}
