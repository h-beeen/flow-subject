package team.flow.upload.file.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import team.flow.upload.global.exception.error.ErrorCode;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;

@Getter
@RequiredArgsConstructor
public enum FileExtensionError implements ErrorCode {

    INVALID_FILE_NAME("파일 확장자 이름을 입력해주세요.", BAD_REQUEST, "F_001"),
    TOO_LONG_FILE_LENGTH("파일 확장자 이름의 최대 길이는 20자입니다.", BAD_REQUEST, "F_002"),
    CONTAIN_INVALID_CHAR("파일 확장자는 영문과 숫자로만 구성되어야 합니다.", BAD_REQUEST, "F_003"),
    DUPLICATED_EXTENSION_IN_CUSTOM("해당 확장자는 이미 커스텀 확장자로 지정되어 있습니다.", CONFLICT, "F_003"),
    DUPLICATED_EXTENSION_IN_FIXED("해당 확장자는 이미 고정 확장자로 지정되어 있습니다.", CONFLICT, "F_004"),
    RESTRICT_FIXED_EXTENSION("해당 확장자는 업로드가 금지된 고정 확장자로 지정되어 있습니다.", BAD_REQUEST, "F_005"),
    RESTRICT_CUSTOM_EXTENSION("해당 확장자는 업로드가 금지된 커스텀 확장자로 지정되어 있습니다.", BAD_REQUEST, "F_006");

    private final String message;
    private final HttpStatus status;
    private final String code;
}

