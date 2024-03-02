package team.flow.upload.file.presentation.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Transactional
@RequiredArgsConstructor
public class FileController {

    @GetMapping("/file/new")
    public String newFileForm(Model model) {
        return "file/new";
    }

    @GetMapping("/file/result")
    public String resultForm(Model model) {
        return "file/result";
    }
}
