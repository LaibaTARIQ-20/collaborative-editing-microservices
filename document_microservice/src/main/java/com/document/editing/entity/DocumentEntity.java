package com.document.editing.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    @Column(name = "user_id", nullable = false, length = 2)

    private Long userId;
    @Column(name = "status", nullable = false, length = 2)
    private Integer status;

    @Column(name = "created_date", updatable = false)
    private Timestamp createdDate;

    @Column(name = "created_by", updatable = false)
    private Long createdBy;

    @Column(name = "modified_date", insertable = false)
    private Timestamp lastUpdate;

    @Column(name = "modified_by", insertable = false)
    private Long lastUpdateBy;

    @Column(name = "path",insertable = false)
    private  String path;
}
