package com.user.authentication.user_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Timestamp;


@Data
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "int_status", nullable = false, length = 2)
    private Integer status;

    @Column(name = "dat_created_date", updatable = false)
    private Timestamp createdDate;

    @Column(name = "int_created_by", updatable = false)
    private Long createdBy;

    @Column(name = "dat_modified_date", insertable = false)
    private Timestamp lastUpdate;

    @Column(name = "int_modified_by", insertable = false)
    private Long lastUpdateBy;
}
