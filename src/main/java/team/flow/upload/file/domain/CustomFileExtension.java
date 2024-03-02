package team.flow.upload.file.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.flow.upload.global.auditing.BaseEntity;
import team.flow.upload.global.exception.BusinessException;

import java.util.regex.Pattern;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;
import static team.flow.upload.file.exception.FileExtensionError.*;

@Entity
@Getter
@Table(name = "t_custom_file_extension")
@NoArgsConstructor(access = PROTECTED)
public class CustomFileExtension extends BaseEntity {

    @Id
    @Column(name = "custom_file_extension_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Builder
    private CustomFileExtension(String name) {
        validateName(name);
        this.name = name.toLowerCase();
    }

    // Static Factory Method ==//
    public static CustomFileExtension from(String name) {
        return CustomFileExtension.builder()
                .name(name)
                .build();
    }

    //== Error Handling Method ==//
    private void validateName(String name) {
        final String REGEX = "^[a-zA-Z0-9]+$";

        if (name.isEmpty()) {
            throw BusinessException.of(INVALID_FILE_NAME);
        } else if (name.length() > 20) {
            throw BusinessException.of(TOO_LONG_FILE_LENGTH);
        } else if (!Pattern.matches(REGEX, name)) {
            throw BusinessException.of(CONTAIN_INVALID_CHAR);
        }
    }
}
