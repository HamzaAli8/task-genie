package com.hamza.taskgenie.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class BaseEntity {

    @Column(updatable = false)
    private LocalDateTime createdDate;

    @Column(insertable = false)
    private LocalDateTime updatedDate;

    @Column(updatable = false)
    private String createdBy;

    @Column(insertable = false)
    private String updatedBy;
}
