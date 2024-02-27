package team.flow.upload.file.application.dto.response;

import team.flow.upload.file.domain.FixedFileExtension;

import java.util.List;

public record FixedFileExtensionResponse(
        Long id,
        String fileExtensionType,
        boolean isRestricted
) {
    public static List<FixedFileExtensionResponse> from(List<FixedFileExtension> fixedFileExtensions) {
        return fixedFileExtensions.stream()
                .map(fixedFileExtension -> new FixedFileExtensionResponse(
                        fixedFileExtension.getId(),
                        fixedFileExtension.getFileExtensionType().toString().toLowerCase(),
                        fixedFileExtension.isRestricted()))
                .toList();

    }
}
