package com.document.editing.service.serviceImpl;

import com.document.editing.dto.requestdto.DocumentRequestDTO;
import com.document.editing.service.DocumentService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class DocumentServiceImpl implements DocumentService {
    private final String directoryPath = "D:/"; // Directory where the file will be created

    @Override
    public String createFile(DocumentRequestDTO documentRequestDTO) {
        String filePath = directoryPath + documentRequestDTO.getFileName() + "." + documentRequestDTO.getFileType();
        File file = new File(filePath);
        try {
            // Create the file
            if (file.createNewFile()) {
                return "File created successfully at: " + filePath;
            } else {
                return "File already exists at: " + filePath;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "An error occurred while creating the file: " + e.getMessage();
        }
    }
}
