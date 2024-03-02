package team.flow.upload.file.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import team.flow.upload.file.domain.CustomFileExtension;

@Transactional
public interface CustomFileExtensionRepository extends JpaRepository<CustomFileExtension, Long> {
    boolean existsByName(String name);
}
