package team.flow.upload.file.application.dto.response;

import team.flow.upload.file.domain.CustomFileExtension;

import java.util.List;

public record CustomFileExtensionResponse(
        Long id,
        String name,
        String createdDateTime
) {
    public static List<CustomFileExtensionResponse> from(List<CustomFileExtension> fixedFileExtensions) {
        return fixedFileExtensions.stream()
                .map(fixedFileExtension -> new CustomFileExtensionResponse(
                        fixedFileExtension.getId(),
                        fixedFileExtension.getName(),
                        fixedFileExtension.getCreatedDate().toString().replace("T", " ")))
                .toList();
    }
}
