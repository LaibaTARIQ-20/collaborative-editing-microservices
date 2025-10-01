package com.document.editing.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "document_table")  // Add this line
public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "created_date", updatable = false)
    private Timestamp createdDate;

    @Column(name = "created_by", updatable = false)
    private Long createdBy;

    @Column(name = "modified_date", insertable = false)
    private Timestamp lastUpdate;

    @Column(name = "modified_by", insertable = false)
    private Long lastUpdateBy;

    @Column(name = "path")
    private String path;

    @PrePersist
    protected void onCreate() {
        createdDate = new Timestamp(System.currentTimeMillis());
    }
}