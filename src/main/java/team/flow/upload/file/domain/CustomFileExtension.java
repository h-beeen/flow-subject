package team.flow.upload.file.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.flow.upload.global.auditing.BaseEntity;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

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
        this.name = name;
    }

    public static CustomFileExtension from(String name) {
        return CustomFileExtension.builder()
                .name(name)
                .build();
    }
}
