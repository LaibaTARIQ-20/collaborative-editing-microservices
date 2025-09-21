package com.document.editing.dto.requestdto;

import lombok.Data;

@Data
public class RevertVersionRequestDTO {
    private Long documentId;
    private int versionNumber;
    private  Long modifiedBy;
}
