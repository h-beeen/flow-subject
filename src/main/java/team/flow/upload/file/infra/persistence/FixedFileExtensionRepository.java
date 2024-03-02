package team.flow.upload.file.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import team.flow.upload.file.domain.FixedFileExtension;
import team.flow.upload.file.domain.constants.FileExtensionType;

import java.util.List;

@Transactional
public interface FixedFileExtensionRepository extends JpaRepository<FixedFileExtension, Long> {

    @Query("SELECT fe.fileExtensionType FROM FixedFileExtension fe WHERE fe.isRestricted = true")
    List<FileExtensionType> findRestrictedFileTypes();
}
