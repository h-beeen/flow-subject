package team.flow.upload.file.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team.flow.upload.file.infra.FileManager;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final FileManager fileManager;

    public String uploadfile(MultipartFile file) {
        return fileManager.uploadFile(file);
    }
}
