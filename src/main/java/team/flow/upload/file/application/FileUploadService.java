package team.flow.upload.file.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team.flow.upload.file.exception.FileError;
import team.flow.upload.file.exception.FileExtensionError;
import team.flow.upload.file.infra.FileManager;
import team.flow.upload.global.exception.BusinessException;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final FileManager fileManager;
    private final CustomFileExtensionService customFileExtensionService;
    private final FixedFileExtensionService fixedFileExtensionService;

    public String uploadFile(MultipartFile file) {
        validateFileEmpty(file);
        String fileExtension = extractFileExtension(Objects.requireNonNull(file.getOriginalFilename()));
        if (hasFileExtension(fileExtension)) {
            validateRestrictFileExtension(fileExtension);
        }
        return fileManager.uploadFile(file);

    }

    private static boolean hasFileExtension(String fileExtension) {
        return !fileExtension.isBlank() && !fileExtension.isEmpty();
    }

    private static void validateFileEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw BusinessException.of(FileError.EMPTY_FILE);
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
