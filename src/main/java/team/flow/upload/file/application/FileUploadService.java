package team.flow.upload.file.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team.flow.upload.file.domain.UploadedFile;
import team.flow.upload.file.exception.FileError;
import team.flow.upload.file.exception.FileExtensionError;
import team.flow.upload.file.infra.FileManager;
import team.flow.upload.file.infra.persistence.UploadedFileRepository;
import team.flow.upload.global.exception.BusinessException;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final FileManager fileManager;
    private final CustomFileExtensionService customFileExtensionService;
    private final FixedFileExtensionService fixedFileExtensionService;
    private final UploadedFileRepository uploadedFileRepository;

    public void uploadFile(MultipartFile file) {
        validateFileEmpty(file);
        validateDuplicatedFileName(file.getOriginalFilename());

        String fileExtension = extractFileExtension(Objects.requireNonNull(file.getOriginalFilename()));
        if (hasFileExtension(fileExtension)) {
            validateRestrictFileExtension(fileExtension);
        }
        String fileUrl = fileManager.uploadFile(file);
        uploadedFileRepository.save(UploadedFile.of(fileUrl, file.getOriginalFilename()));
    }

    private boolean hasFileExtension(String fileExtension) {
        return !fileExtension.isBlank() && !fileExtension.isEmpty();
    }

    private void validateFileEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw BusinessException.of(FileError.EMPTY_FILE);
        }
    }

    private void validateDuplicatedFileName(String originalFileName) {
        log.warn("{}", originalFileName);
        log.warn("{}", uploadedFileRepository.findById(1L));

        if (uploadedFileRepository.existsByOriginalFileName(originalFileName)) {
            throw BusinessException.of(FileError.DUPLICATED_FILE);
        }
    }

    private void validateRestrictFileExtension(String fileExtension) {
        if (fixedFileExtensionService.isRestrictFixedFileExtension(fileExtension)) {
            throw BusinessException.of(FileExtensionError.RESTRICT_FIXED_EXTENSION);
        } else if (customFileExtensionService.isRestrictCustomFileExtension(fileExtension)) {
            throw BusinessException.of(FileExtensionError.RESTRICT_CUSTOM_EXTENSION);
        }
    }

    private String extractFileExtension(String originalFilename) {
        int index = originalFilename.lastIndexOf(".");
        return originalFilename.substring(index + 1);
    }
}
