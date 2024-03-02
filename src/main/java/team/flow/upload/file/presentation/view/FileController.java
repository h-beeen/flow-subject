package team.flow.upload.file.presentation.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import team.flow.upload.file.application.dto.response.CustomFileExtensionResponse;
import team.flow.upload.file.application.dto.response.FixedFileExtensionResponse;
import team.flow.upload.file.domain.UploadedFile;
import team.flow.upload.file.infra.persistence.UploadedFileRepository;

import java.util.List;

@Controller
@Transactional
@RequiredArgsConstructor
public class FileController {

    private final UploadedFileRepository uploadedFileRepository;

    @GetMapping("/file/new")
    public String newFileForm(Model model) {
        return "file/new";
    }

    @GetMapping("/file/result")
    public String resultForm(Model model) {
        return "file/result";
    }

    @GetMapping("/files")
    public String getUploadedFiles(Model model) {
        List<UploadedFile> result = uploadedFileRepository.findAll();

        model.addAttribute("uploadedFiles", result);

        return "file/files";
    }
}
