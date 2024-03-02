package team.flow.upload.file.infra;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team.flow.upload.file.exception.FileError;
import team.flow.upload.global.exception.BusinessException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import static team.flow.upload.file.exception.FileError.EMPTY_FILE;
import static team.flow.upload.file.exception.FileError.UPLOAD_FAILED;

@Slf4j
@Service
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

    public ResponseEntity<byte[]> downloadFile(String storedFileName) {
        try {
            S3Object o = amazonS3Client.getObject(new GetObjectRequest(bucketName, storedFileName));
            S3ObjectInputStream objectInputStream = o.getObjectContent();
            byte[] bytes = IOUtils.toByteArray(objectInputStream);

            String fileName = URLEncoder.encode(storedFileName, "UTF-8").replaceAll("\\+", "%20");
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            httpHeaders.setContentLength(bytes.length);
            httpHeaders.setContentDispositionFormData("attachment", fileName);
            return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
        } catch (Exception e) {
            throw BusinessException.of(FileError.UNKNOWN_ERROR);
        }
    }

    private void validateFileExist(MultipartFile file) {
        if (file.isEmpty()) {
            throw BusinessException.of(EMPTY_FILE);
        }
    }

    public ResponseEntity<byte[]> getObject(String storedFileName) throws IOException{
        S3Object o = amazonS3Client.getObject(new GetObjectRequest(bucketName, storedFileName));
        S3ObjectInputStream objectInputStream = o.getObjectContent();
        byte[] bytes = IOUtils.toByteArray(objectInputStream);

        String fileName = URLEncoder.encode(storedFileName, "UTF-8").replaceAll("\\+", "%20");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentLength(bytes.length);
        httpHeaders.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);

    }
}


