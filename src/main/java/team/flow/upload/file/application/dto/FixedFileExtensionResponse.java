package team.flow.upload.file.application.dto;

import team.flow.upload.file.domain.FixedFileExtension;

import java.util.List;

public record FixedFileExtensionResponse(
        String fileExtensionType,
        boolean isRestricted
) {
    public static List<FixedFileExtensionResponse> of(List<FixedFileExtension> fixedFileExtensions) {
        return fixedFileExtensions.stream()
                .map(fixedFileExtension -> new FixedFileExtensionResponse(
                        fixedFileExtension.getFileExtensionType().toString().toLowerCase(),
                        fixedFileExtension.isRestricted()))
                .toList();
    }
}
