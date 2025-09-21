package com.document.editing.service.serviceImpl;

import com.document.editing.dto.requestdto.FileRequestDTO;
import com.document.editing.dto.requestdto.RevertVersionRequestDTO;
import com.document.editing.dto.responsedto.DocumentVersionResponseDTO;
import com.document.editing.entity.DocumentEntity;
import com.document.editing.mapper.DocumentMapper;
import com.document.editing.repository.DocumentRepository;
import com.document.editing.service.FileReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileReadingServiceImpl implements FileReadingService {
//    private final String directoryPath = "D:/"; // Directory to watch
//    private final String fileName = "test.txt"; // File to monitor for changes

    @Autowired
    private RestTemplate restTemplate;

    private final String versionServiceUrl = "http://localhost:8082/version/retrieve";
    @Autowired
   private DocumentMapper documentMapper;
    @Autowired
   private DocumentRepository documentRepository;

    @Override
    public void startWatching(FileRequestDTO fileRequestDTO) {
        try {
            String driveName=fileRequestDTO.getDrive()+":/";
            String fileName=fileRequestDTO.getFileName();
            String path1=driveName+fileName;
            System.out.println(path1);
            ProcessBuilder pb = new ProcessBuilder("Notepad.exe", path1);
            pb.start();
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(fileRequestDTO.getDrive()+":/");
            path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
            while (true) {
                WatchKey key;
                try {
                    key = watchService.take();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    return;
                }
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();

                    // We only care about modify events
                    if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                        Path changed = (Path) event.context();
                        if (changed.endsWith(fileRequestDTO.getFileName())) {
                            readFileContent(driveName,fileName);
                        }
                    }
                }
                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public String revertToVersion(RevertVersionRequestDTO revertVersionRequestDTO) {
        ResponseEntity<DocumentVersionResponseDTO> response = restTemplate.getForEntity(
                versionServiceUrl + "?documentId=" + revertVersionRequestDTO.getDocumentId() + "&versionNumber=" + revertVersionRequestDTO.getVersionNumber(),
                DocumentVersionResponseDTO.class);
        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new RuntimeException("Failed to retrieve version");
        }
        DocumentVersionResponseDTO documentVersion = response.getBody();
        DocumentEntity document = documentRepository.findById(revertVersionRequestDTO.getDocumentId())
                .orElseThrow(() -> new RuntimeException("Document not found"));
        document.setContent(documentVersion.getContent());
        document.setLastUpdateBy(revertVersionRequestDTO.getModifiedBy());
        document.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        DocumentEntity documentEntity=  documentRepository.save(document);
        return "Revert successfully";  //saveDocumentVersion(documentVersion.getContent(), revertVersionRequestDTO.getDocumentId(), revertVersionRequestDTO.getModifiedBy());
    }

    private void readFileContent(String driveName, String fileName)  {
        try {
            Path filePath = Paths.get(driveName, fileName);
            String content = Files.readString(filePath);
         DocumentEntity documentEntity= documentRepository.save(documentMapper.savedocument(content, 4L,filePath.toString()));
            saveDocumentVersion(content,documentEntity.getId(),documentEntity.getUserId(),filePath.toString());
            System.out.println("File content:\n" + content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String saveDocumentVersion(String content, Long documentId, Long modifiedBy,String path) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8082/version/save";
        Map<String, Object> request = new HashMap<>();
        request.put("content", content);
        request.put("documentId", documentId);
        request.put("modifiedBy", modifiedBy);
        request.put("path",path);
        return restTemplate.postForObject(url, request, String.class);
    }

    }
