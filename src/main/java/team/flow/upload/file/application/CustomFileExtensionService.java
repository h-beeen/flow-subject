package team.flow.upload.file.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.flow.upload.file.application.dto.request.CustomFileExtensionRequest;
import team.flow.upload.file.application.dto.request.FileExtensionIdRequest;
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
        List<CustomFileExtension> result = customFileExtensionRepository.findAll();
        return CustomFileExtensionResponse.from(result);
    }

    public void deleteCustomFileExtensions(FileExtensionIdRequest request) {
        Long customFileExtensionId = request.id();
        customFileExtensionRepository.deleteById(customFileExtensionId);
    }

    public void createCustomFileExtensions(CustomFileExtensionRequest request) {
        String extensionName = request.name();

        validateDuplicatedNameInFixedFileExtension(extensionName);
        validateDuplicatedNameInCustomFileExtension(extensionName);

        CustomFileExtension extension = CustomFileExtension.from(extensionName);
        customFileExtensionRepository.save(extension);
    }

    private void validateDuplicatedNameInCustomFileExtension(String extensionName) {
        List<FixedFileExtension> fixedFileExtensions = fixedFileExtensionRepository.findAll();
        List<String> extensionNames = fixedFileExtensions.stream()
                .map(fixedFileExtension -> fixedFileExtension.getFileExtensionType().getLowerCase())
                .toList();

        if (extensionNames.contains(extensionName)) {
            throw BusinessException.of(FileExtensionError.DUPLICATED_EXTENSION_IN_CUSTOM);
        }
    }

    private void validateDuplicatedNameInFixedFileExtension(String extensionName) {
        boolean isDuplicated = customFileExtensionRepository.existsByName(extensionName);

        if (isDuplicated) {
            throw BusinessException.of(FileExtensionError.DUPLICATED_EXTENSION_IN_FIXED);
        }
    }
}
