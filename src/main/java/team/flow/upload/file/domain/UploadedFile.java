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
@Table(name = "t_uploaded_file")
@NoArgsConstructor(access = PROTECTED)
public class UploadedFile extends BaseEntity {

    @Id
    @Column(name = "uploaded_file_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "original_name")
    private String originalFileName;

    @Builder
    private UploadedFile(
            String url,
            String originalFileName
    ) {
        this.url = url;
        this.originalFileName = originalFileName;
    }

    public static UploadedFile of(
            String url,
            String originalFileName
    ) {
        return UploadedFile.builder()
                .url(url)
                .originalFileName(originalFileName)
                .build();
    }
}
