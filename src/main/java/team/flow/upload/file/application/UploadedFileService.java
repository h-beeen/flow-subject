package team.flow.upload.file.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.flow.upload.file.domain.UploadedFile;
import team.flow.upload.file.exception.FileError;
import team.flow.upload.file.infra.persistence.UploadedFileRepository;
import team.flow.upload.global.exception.BusinessException;

@Service
@RequiredArgsConstructor
public class UploadedFileService {

    private final UploadedFileRepository uploadedFileRepository;

    public void deleteFile(Long fileId) {
        uploadedFileRepository.deleteById(fileId);
    }

    public UploadedFile findByFileId(Long fileId) {
        return uploadedFileRepository.findById(fileId)
                .orElseThrow(() -> BusinessException.of(FileError.UNKNOWN_ERROR));
    }
}
