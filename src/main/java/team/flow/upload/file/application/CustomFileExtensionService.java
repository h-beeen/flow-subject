package team.flow.upload.file.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.flow.upload.file.application.dto.request.CustomFileExtensionRequest;
import team.flow.upload.file.application.dto.request.IdRequest;
import team.flow.upload.file.application.dto.response.CustomFileExtensionResponse;
import team.flow.upload.file.domain.CustomFileExtension;
import team.flow.upload.file.domain.FixedFileExtension;
import team.flow.upload.file.exception.FileExtensionError;
import team.flow.upload.file.infra.persistence.CustomFileExtensionRepository;
import team.flow.upload.file.infra.persistence.FixedFileExtensionRepository;
import team.flow.upload.global.exception.BusinessException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomFileExtensionService {

    private final FixedFileExtensionRepository fixedFileExtensionRepository;
    private final CustomFileExtensionRepository customFileExtensionRepository;

    public List<CustomFileExtensionResponse> getCustomFileExtensions() {
        List<CustomFileExtension> results = customFileExtensionRepository.findAll();
        return CustomFileExtensionResponse.from(results);
    }

    public void deleteCustomFileExtensions(IdRequest request) {
        Long customFileExtensionId = request.id();
        customFileExtensionRepository.deleteById(customFileExtensionId);
    }

    public void createCustomFileExtensions(CustomFileExtensionRequest request) {
        String extensionName = request.name();

        validateDuplicatedNameInCustomFileExtension(extensionName);
        validateDuplicatedNameInFixedFileExtension(extensionName);

        CustomFileExtension extension = CustomFileExtension.from(extensionName);
        customFileExtensionRepository.save(extension);
    }

    public void validateDuplicatedNameInFixedFileExtension(String extensionName) {
        String lowerCaseExtensionName = extensionName.toLowerCase();

        List<FixedFileExtension> fixedFileExtensions = fixedFileExtensionRepository.findAll();
        List<String> extensionNames = fixedFileExtensions.stream()
                .map(fixedFileExtension -> fixedFileExtension.getFileExtensionType().getLowerCase())
                .toList();

        if (extensionNames.contains(lowerCaseExtensionName)) {
            throw BusinessException.of(FileExtensionError.DUPLICATED_EXTENSION_IN_FIXED);
        }
    }

    public boolean isRestrictCustomFileExtension(String extensionName) {
        List<CustomFileExtension> result = customFileExtensionRepository.findAll();

        String extensionNameLowerCase = extensionName.toLowerCase();
        return result.stream()
                .anyMatch(customFileExtension -> customFileExtension.getName().toLowerCase().equals(extensionNameLowerCase));
    }

    private void validateDuplicatedNameInCustomFileExtension(String extensionName) {
        final int EXCEEDS_LIMIT = 200;

        String lowerCaseExtensionName = extensionName.toLowerCase();
        boolean isDuplicated = customFileExtensionRepository.existsByName(lowerCaseExtensionName);
        long elementCount = customFileExtensionRepository.count();
        if (isDuplicated) {
            throw BusinessException.of(FileExtensionError.DUPLICATED_EXTENSION_IN_CUSTOM);
        }
        if (elementCount > EXCEEDS_LIMIT){
            throw BusinessException.of(FileExtensionError.EXCEEDS_LIMITS);
        }
    }
}
