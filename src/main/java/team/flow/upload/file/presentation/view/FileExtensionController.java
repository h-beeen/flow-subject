package team.flow.upload.file.presentation.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import team.flow.upload.file.application.CustomFileExtensionService;
import team.flow.upload.file.application.FixedFileExtensionService;
import team.flow.upload.file.application.dto.response.CustomFileExtensionResponse;
import team.flow.upload.file.application.dto.response.FixedFileExtensionResponse;

import java.util.List;

@Controller
@Transactional
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileExtensionController {

    private final FixedFileExtensionService fixedFileExtensionService;
    private final CustomFileExtensionService customFileExtensionService;

    @GetMapping("/restrict")
    public String getRestrictExtensions(Model model) {
        List<FixedFileExtensionResponse> fixedFileExtensions = fixedFileExtensionService.getFixedFileExtensions();
        List<CustomFileExtensionResponse> customFileExtensions = customFileExtensionService.getCustomFileExtensions();

        model.addAttribute("fixedFileExtensions", fixedFileExtensions);
        model.addAttribute("customFileExtensions", customFileExtensions);
        model.addAttribute("customFileCount", customFileExtensions.size());

        return "file/restrict";
    }
}
