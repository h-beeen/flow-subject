package team.flow.upload.file.presentation.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import team.flow.upload.file.application.CustomFileExtensionService;
import team.flow.upload.file.application.FixedFileExtensionService;
import team.flow.upload.file.application.dto.request.CustomFileExtensionRequest;
import team.flow.upload.file.application.dto.request.FileExtensionIdRequest;

@RestController
@Transactional
@RequestMapping("/file/restrict")
@RequiredArgsConstructor
public class RestrictFileExtensionController {

    private final FixedFileExtensionService fixedFileExtensionService;
    private final CustomFileExtensionService customFileExtensionService;

    @PatchMapping("/fixed")
    public ResponseEntity<Void> updateFixedFileExtensionStatus(@RequestBody FileExtensionIdRequest request) {
        fixedFileExtensionService.changeFixedFileExtensionCheckBox(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/custom")
    public ResponseEntity<Void> deleteCustomFileExtension(@RequestBody FileExtensionIdRequest request) {
        customFileExtensionService.deleteCustomFileExtensions(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/custom")
    public ResponseEntity<Void> createCustomFileExtension(@RequestBody CustomFileExtensionRequest request) {
        customFileExtensionService.createCustomFileExtensions(request);
        return ResponseEntity.ok().build();
    }
}
