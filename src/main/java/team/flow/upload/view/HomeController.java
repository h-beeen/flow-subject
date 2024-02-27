package team.flow.upload.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/file/restrict")
    public String restrictFile() {
        return "/file/restrict";
    }
}
