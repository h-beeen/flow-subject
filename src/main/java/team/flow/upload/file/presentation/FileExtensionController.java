package team.flow.upload.file.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.flow.upload.file.application.FixedFileExtensionService;
import team.flow.upload.file.application.dto.request.FixedFileExtensionIdRequest;
import team.flow.upload.file.application.dto.response.FixedFileExtensionResponse;

import java.util.List;

@Controller
@Transactional
@RequestMapping("/file/restrict")
@RequiredArgsConstructor
public class FileExtensionController {

    private final FixedFileExtensionService fixedFileExtensionService;

    @GetMapping
    public String getRestrictExtensions(Model model) {
        List<FixedFileExtensionResponse> fixedFileExtensions = fixedFileExtensionService.getFixedFileExtensions();
        model.addAttribute("fixedFileExtensions", fixedFileExtensions);
        return "file/restrict";
    }

    @PatchMapping
    public void updateFixedFileExtensionStatus(@RequestBody FixedFileExtensionIdRequest request) {
        fixedFileExtensionService.changeFixedFileExtensionCheckBox(request.id());
    }

}
