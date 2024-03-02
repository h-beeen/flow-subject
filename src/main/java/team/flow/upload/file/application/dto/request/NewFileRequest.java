package team.flow.upload.file.application.dto.request;

import org.springframework.web.multipart.MultipartFile;

public record NewFileRequest(
        MultipartFile file
) {
}
