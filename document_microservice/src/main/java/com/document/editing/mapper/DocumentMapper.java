package com.document.editing.mapper;

import com.document.editing.entity.DocumentEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapper {
    @Autowired
    private ModelMapper modelMapper;
    public DocumentEntity savedocument(String content, Long userId,String path)
    {
        DocumentEntity documentEntity=new DocumentEntity();
        documentEntity.setContent(content);
        documentEntity.setStatus(1);
        documentEntity.setUserId(userId);
        documentEntity.setPath(path);

        return  documentEntity;

    }
}
