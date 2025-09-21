package com.version.tracking.service;

import com.version.tracking.dto.VersionResponseDTO;
import com.version.tracking.entity.VersionEntity;
import org.springframework.http.ResponseEntity;

public interface VersionService {
    public void saveNewVersion(String content, Long documentId, Long modifiedBy,String path) ;
    public VersionEntity getVersion(
             Long documentId,
            int versionNumber) ;
    }
