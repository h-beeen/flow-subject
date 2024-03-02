package team.flow.upload.file.infra;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import team.flow.upload.global.exception.BusinessException;

import java.io.IOException;
import java.io.InputStream;

import static team.flow.upload.file.exception.FileError.EMPTY_FILE;
import static team.flow.upload.file.exception.FileError.UPLOAD_FAILED;

@Component
public class FileManager {

    private final AmazonS3Client amazonS3Client;
    private final String bucketName;

    public FileManager(
            AmazonS3Client amazonS3Client,
            @Value("${cloud.aws.s3.bucket}") String bucketName
    ) {
        this.amazonS3Client = amazonS3Client;
        this.bucketName = bucketName;
    }

    public String uploadFile(MultipartFile file) {
        validateFileExist(file);

        String originalFilename = file.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        try (InputStream inputStream = file.getInputStream()) {
            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    bucketName,
                    originalFilename,
                    inputStream,
                    metadata).withCannedAcl(CannedAccessControlList.PublicRead);

            amazonS3Client.putObject(putObjectRequest);
        } catch (IOException e) {
            throw BusinessException.of(UPLOAD_FAILED);
        }

        return amazonS3Client.getUrl(bucketName, originalFilename).toString();
    }

    private void validateFileExist(MultipartFile file) {
        if (file.isEmpty()) {
            throw BusinessException.of(EMPTY_FILE);
        }
    }
}


