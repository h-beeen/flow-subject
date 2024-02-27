package team.flow.upload.view.file.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import team.flow.upload.file.application.FixedFileExtensionService;
import team.flow.upload.file.application.dto.FixedFileExtensionResponse;

import java.util.List;

@Controller
@Transactional
@RequiredArgsConstructor
public class FileExtensionController {

    private final FixedFileExtensionService fixedFileExtensionService;

    @GetMapping("/file/restrict")
    public String getRestrictExtensions(Model model) {
        List<FixedFileExtensionResponse> fixedFileExtensions = fixedFileExtensionService.getFixedFileExtensions();
        model.addAttribute("fixedFileExtensions", fixedFileExtensions);
        return "file/restrict";
    }
}
