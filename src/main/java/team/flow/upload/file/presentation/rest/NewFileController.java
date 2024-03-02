package team.flow.upload.file.presentation.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.flow.upload.file.application.dto.request.NewFileRequest;

@RestController
@Transactional
@RequestMapping("/file/new")
@RequiredArgsConstructor
@Slf4j
public class NewFileController {

    @PostMapping
    public ResponseEntity<Void> newFileForm(@ModelAttribute NewFileRequest request) {
        log.warn("{}", request.file().getOriginalFilename());
        return ResponseEntity.ok().build();
    }
}
