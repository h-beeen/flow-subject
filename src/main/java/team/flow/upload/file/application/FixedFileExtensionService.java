package team.flow.upload.file.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.flow.upload.file.application.dto.request.FileExtensionIdRequest;
import team.flow.upload.file.application.dto.response.FixedFileExtensionResponse;
import team.flow.upload.file.domain.FixedFileExtension;
import team.flow.upload.file.infra.persistence.FixedFileExtensionRepository;
import team.flow.upload.global.exception.BusinessException;

import java.util.List;

import static team.flow.upload.global.exception.error.GlobalError.GLOBAL_NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
public class FixedFileExtensionService {

    private final FixedFileExtensionRepository fixedFileExtensionRepository;

    public List<FixedFileExtensionResponse> getFixedFileExtensions() {
        List<FixedFileExtension> fixedFileExtensions = fixedFileExtensionRepository.findAll();
        return FixedFileExtensionResponse.from(fixedFileExtensions);
    }

    public void changeFixedFileExtensionCheckBox(FileExtensionIdRequest request) {
        Long fixedFileExtensionId = request.id();
        FixedFileExtension fixedFileExtension = fixedFileExtensionRepository.findById(fixedFileExtensionId)
                .orElseThrow(() -> BusinessException.of(GLOBAL_NOT_FOUND));

        fixedFileExtension.changeRestrictStatus();
    }

    private String extractExt(String originalFilename) {
        int index = originalFilename.lastIndexOf(".");
        return originalFilename.substring(index);
    }
}
