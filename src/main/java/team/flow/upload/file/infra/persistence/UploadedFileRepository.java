package team.flow.upload.file.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import team.flow.upload.file.domain.UploadedFile;

@Transactional
public interface UploadedFileRepository extends JpaRepository <UploadedFile, Long> {
    boolean existsByOriginalFileName(String originalFileName);
}
