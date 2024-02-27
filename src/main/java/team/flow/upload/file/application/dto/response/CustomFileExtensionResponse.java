package team.flow.upload.file.application.dto.response;

import team.flow.upload.file.domain.CustomFileExtension;

import java.util.List;

public record CustomFileExtensionResponse(
        Long id,
        String name
) {
    public static List<CustomFileExtensionResponse> from(List<CustomFileExtension> fixedFileExtensions) {
        return fixedFileExtensions.stream()
                .map(fixedFileExtension -> new CustomFileExtensionResponse(
                        fixedFileExtension.getId(),
                        fixedFileExtension.getName()))
                .toList();
    }
}
