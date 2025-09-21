package com.version.tracking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Entity
@Data
public class VersionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long documentId;
    @Column(nullable = false)
    private int versionNumber;
    @Lob
    private String content;
    @Column(nullable = false)
    private LocalDateTime timestamp;
    @Column(nullable = false)
    private Long userId;

    private  String path;
}
