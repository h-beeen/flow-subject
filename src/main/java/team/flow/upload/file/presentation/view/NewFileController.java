package team.flow.upload.file.presentation.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import team.flow.upload.file.application.FileUploadService;

@Controller
@Transactional
@RequestMapping("/file/new")
@RequiredArgsConstructor
public class NewFileController {

    private final FileUploadService fileUploadService;

    @PostMapping
    public String fileUpload(
            Model model,
            @RequestParam(value = "file") MultipartFile file
    ) {
        try {
            fileUploadService.uploadFile(file);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "/file/result";
        }
        model.addAttribute("message", file.getOriginalFilename() + " 파일 업로드 성공!");
        return "/file/result";
    }
}
