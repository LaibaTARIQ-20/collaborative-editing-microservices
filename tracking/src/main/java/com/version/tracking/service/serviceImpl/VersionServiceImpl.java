package com.version.tracking.service.serviceImpl;

import com.version.tracking.entity.VersionEntity;
import com.version.tracking.repository.VersionRepository;
import com.version.tracking.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VersionServiceImpl implements VersionService {
    @Autowired
    private VersionRepository versionRepository;
    @Override
    public void saveNewVersion(String content, Long documentId, Long modifiedBy,String path) {
        int latestVersionNumber = versionRepository
                .findMaxVersionNumberByDocumentId(documentId)
                .orElse(0);
        System.out.println("Latest version number: " + latestVersionNumber);
        VersionEntity newVersion = new VersionEntity();
        newVersion.setDocumentId(documentId);
        newVersion.setVersionNumber( latestVersionNumber+ 1);
        newVersion.setContent(content);
        newVersion.setUserId(modifiedBy);
        newVersion.setTimestamp(LocalDateTime.now());
        newVersion.setPath(path);
      VersionEntity versionEntity=  versionRepository.save(newVersion);
    }

    @Override
    public VersionEntity getVersion(Long documentId, int versionNumber) {
        VersionEntity version = versionRepository
                .findByDocumentIdAndVersionNumber(documentId, versionNumber);
        return version;
    }

}
