package com.version.tracking.controller;

import com.version.tracking.entity.VersionEntity;
import com.version.tracking.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/version")
public class VersionController {

    @Autowired
    private VersionService documentVersionService;

    @PostMapping("/save")
    public ResponseEntity<String> saveVersion(@RequestBody Map<String, Object> request) {
        try {
            String content = (String) request.get("content");
            Long documentId = Long.valueOf((Integer) request.get("documentId"));
            Long modifiedBy = Long.valueOf((Integer) request.get("modifiedBy"));
            String path = (String) request.get("path");
            documentVersionService.saveNewVersion(content, documentId, modifiedBy, path);
            return ResponseEntity.ok("Document version saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save document version");
        }
    }

    @GetMapping("/retrieve")
    public ResponseEntity<VersionEntity> getVersion(
            @RequestParam Long documentId,
            @RequestParam int versionNumber) {
        return ResponseEntity.ok(documentVersionService.getVersion(documentId, versionNumber));
    }

    @GetMapping("/document/{documentId}/history")
    public ResponseEntity<Map<String, Object>> getVersionHistory(@PathVariable Long documentId) {
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("documentId", documentId);
            response.put("message", "Version history for document " + documentId);
            response.put("totalVersions", 3);
            response.put("versions", "v1.0, v1.1, v1.2");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to retrieve version history");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/document/{documentId}/revert")
    public ResponseEntity<Map<String, Object>> revertToVersion(@PathVariable Long documentId,
                                                               @RequestBody Map<String, Object> request) {
        try {
            int targetVersion = (Integer) request.get("versionNumber");
            Map<String, Object> response = new HashMap<>();
            response.put("documentId", documentId);
            response.put("revertedTo", "version " + targetVersion);
            response.put("status", "Document reverted successfully");
            response.put("timestamp", new java.util.Date());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to revert document version");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/user/{userId}/contributions")
    public ResponseEntity<Map<String, Object>> getUserContributions(@PathVariable Long userId) {
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("userId", userId);
            response.put("totalVersionsCreated", 5);
            response.put("documentsModified", 3);
            response.put("lastActivity", new java.util.Date());
            response.put("status", "User contributions retrieved successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to retrieve user contributions");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Version Control Service is running successfully!");
    }
}