package team.flow.upload.file.presentation.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import team.flow.upload.file.application.CustomFileExtensionService;
import team.flow.upload.file.application.FixedFileExtensionService;
import team.flow.upload.file.application.dto.request.FileExtensionIdRequest;

@RestController
@Transactional
@RequestMapping("/file/restrict")
@RequiredArgsConstructor
public class FileExtensionRestController {

    private final FixedFileExtensionService fixedFileExtensionService;
    private final CustomFileExtensionService customFileExtensionService;

    @PatchMapping
    public ResponseEntity<Void> updateFixedFileExtensionStatus(@RequestBody FileExtensionIdRequest request) {
        fixedFileExtensionService.changeFixedFileExtensionCheckBox(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCustomFileExtension(@RequestBody FileExtensionIdRequest request) {
        customFileExtensionService.deleteCustomFileExtensions(request);
        return ResponseEntity.ok().build();
    }
}
