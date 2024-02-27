package team.flow.upload.file.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import team.flow.upload.file.domain.CustomFileExtension;

public interface CustomFileExtensionRepository extends JpaRepository<CustomFileExtension, Long> {
}
