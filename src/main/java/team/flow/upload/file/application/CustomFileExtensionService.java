package team.flow.upload.file.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.flow.upload.file.application.dto.request.CustomFileExtensionRequest;
import team.flow.upload.file.application.dto.request.FileExtensionIdRequest;
import team.flow.upload.file.application.dto.response.CustomFileExtensionResponse;
import team.flow.upload.file.domain.CustomFileExtension;
import team.flow.upload.file.infra.persistence.CustomFileExtensionRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomFileExtensionService {

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
        String customFileExtensionName = request.name();
        CustomFileExtension extension = CustomFileExtension.from(customFileExtensionName);
        customFileExtensionRepository.save(extension);
    }
}
