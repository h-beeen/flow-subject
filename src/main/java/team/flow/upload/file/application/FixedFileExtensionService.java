package team.flow.upload.file.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.flow.upload.file.application.dto.FixedFileExtensionResponse;
import team.flow.upload.file.domain.FixedFileExtension;
import team.flow.upload.file.infra.persistence.FixedFileExtensionRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FixedFileExtensionService {

    private final FixedFileExtensionRepository fixedFileExtensionRepository;

    public List<FixedFileExtensionResponse> getFixedFileExtensions() {
        List<FixedFileExtension> fixedFileExtensions = fixedFileExtensionRepository.findAll();
        return FixedFileExtensionResponse.of(fixedFileExtensions);
    }

//    public void updateFixedFileExtensionCheckBox(Long id, boolean isChecked) {
//        fixedFileExtensionRepository.findById(id)
//    }
}
