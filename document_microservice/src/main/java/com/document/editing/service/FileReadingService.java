package com.document.editing.service;

import com.document.editing.dto.requestdto.FileRequestDTO;
import com.document.editing.dto.requestdto.RevertVersionRequestDTO;

public interface FileReadingService {
    public void startWatching(FileRequestDTO fileRequestDTO) ;
    public String revertToVersion(RevertVersionRequestDTO revertVersionRequestDTO);

}
