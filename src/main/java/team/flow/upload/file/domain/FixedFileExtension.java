package team.flow.upload.file.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.flow.upload.file.domain.constants.FileExtensionType;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Table(name = "t_fixed_file_extension")
@NoArgsConstructor(access = PROTECTED)
public class FixedFileExtension {

    @Id
    @Column(name = "fixed_file_extension_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "file_extention_type")
    private FileExtensionType fileExtensionType;

    @Column(name = "is_restricted")
    private boolean isRestricted;

    public void changeRestrictStatus() {
        this.isRestricted = !isRestricted;
    }
}
