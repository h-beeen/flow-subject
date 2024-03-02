package team.flow.upload.file.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.flow.upload.file.infra.persistence.UploadedFileRepository;

@Service
@RequiredArgsConstructor
public class UploadedFileService {

    private final UploadedFileRepository uploadedFileRepository;

    public void deleteFile(Long fileId) {
        uploadedFileRepository.deleteById(fileId);
    }
}
