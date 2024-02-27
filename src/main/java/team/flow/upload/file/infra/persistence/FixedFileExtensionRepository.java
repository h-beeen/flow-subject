package team.flow.upload.file.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import team.flow.upload.file.domain.FixedFileExtension;

@Transactional
public interface FixedFileExtensionRepository extends JpaRepository<FixedFileExtension, Long> {
}
