package team.flow.upload.file.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.flow.upload.file.application.CustomFileExtensionService;
import team.flow.upload.file.application.FixedFileExtensionService;
import team.flow.upload.file.application.dto.request.FileExtensionIdRequest;
import team.flow.upload.file.application.dto.response.CustomFileExtensionResponse;
import team.flow.upload.file.application.dto.response.FixedFileExtensionResponse;

import java.util.List;

@Controller
@Transactional
@RequestMapping("/file/restrict")
@RequiredArgsConstructor
public class FileExtensionController {

    private final FixedFileExtensionService fixedFileExtensionService;
    private final CustomFileExtensionService customFileExtensionService;

    @GetMapping
    public String getRestrictExtensions(Model model) {
        List<FixedFileExtensionResponse> fixedFileExtensions = fixedFileExtensionService.getFixedFileExtensions();
        List<CustomFileExtensionResponse> customFileExtensions = customFileExtensionService.getCustomFileExtensions();

        model.addAttribute("fixedFileExtensions", fixedFileExtensions);
        model.addAttribute("customFileExtensions", customFileExtensions);

        return "file/restrict";
    }

    @PatchMapping
    public void updateFixedFileExtensionStatus(@RequestBody FileExtensionIdRequest request) {
        fixedFileExtensionService.changeFixedFileExtensionCheckBox(request);
    }

    @DeleteMapping
    public void deleteCustomFileExtension(@RequestBody FileExtensionIdRequest request) {
        customFileExtensionService.deleteCustomFileExtensions(request);
    }
}
