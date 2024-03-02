package team.flow.upload.file.presentation.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import team.flow.upload.file.application.FileUploadService;

@Controller
@Transactional
@RequestMapping("/file/new")
@RequiredArgsConstructor
@Slf4j
public class NewFileController {

    private final FileUploadService fileUploadService;


    @PostMapping
    public String fileUpload(@RequestParam(value = "file") MultipartFile file) {
        fileUploadService.uploadFile(file);
        return "redirect:/file/new";
    }
}
