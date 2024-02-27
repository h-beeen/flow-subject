package team.flow.upload.file.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        return FixedFileExtensionResponse.of(fixedFileExtensions);
    }

    public void changeFixedFileExtensionCheckBox(Long id) {
        FixedFileExtension fixedFileExtension = fixedFileExtensionRepository.findById(id)
                .orElseThrow(() -> BusinessException.of(GLOBAL_NOT_FOUND));

        fixedFileExtension.changeRestrictStatus();
    }
}
