package team.flow.upload.file.presentation.rest;

import com.amazonaws.services.s3.model.GetObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import team.flow.upload.file.application.UploadedFileService;
import team.flow.upload.file.application.dto.request.FileNameRequest;
import team.flow.upload.file.application.dto.request.IdRequest;
import team.flow.upload.file.domain.UploadedFile;
import team.flow.upload.file.infra.FileManager;

import java.io.IOException;
import java.io.InputStream;

@RestController
@Transactional
@RequestMapping("/file")
@RequiredArgsConstructor
@Slf4j
public class FileRestController {

    private final UploadedFileService uploadedFileService;
    private final FileManager fileManager;

    @DeleteMapping
    public ResponseEntity<Void> deleteCustomFile(@RequestBody IdRequest request) {
        uploadedFileService.deleteFile(request.id());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<byte[]> download(@RequestParam Long id) throws IOException {
        UploadedFile uploadedFile = uploadedFileService.findByFileId(id);
        return fileManager.getObject(uploadedFile.getOriginalFileName());
    }
}
