package team.flow.upload.file.presentation.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.flow.upload.file.application.UploadedFileService;
import team.flow.upload.file.application.dto.request.IdRequest;

@RestController
@Transactional
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileRestController {

    private final UploadedFileService uploadedFileService;

    @DeleteMapping
    public ResponseEntity<Void> deleteCustomFileExtension(@RequestBody IdRequest request) {
        uploadedFileService.deleteFile(request.id());
        return ResponseEntity.ok().build();
    }
}
