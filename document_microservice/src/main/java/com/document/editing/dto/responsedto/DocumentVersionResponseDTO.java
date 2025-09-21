package com.document.editing.dto.responsedto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DocumentVersionResponseDTO {
    private Long id;
    private Long documentId;
    private int versionNumber;
    private String content;
    private LocalDateTime timestamp;
    private Long userId;
}
