package team.flow.upload.file.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import team.flow.upload.global.exception.error.ErrorCode;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@RequiredArgsConstructor
public enum FileExtensionError implements ErrorCode {

    INVALID_FILE_NAME("파일 확장자 이름을 입력해주세요.", BAD_REQUEST, "F_001"),
    TOO_LONG_FILE_LENGTH("파일 확장자 이름의 최대 길이는 20자입니다.", BAD_REQUEST, "F_002"),
    CONTAIN_INVALID_CHAR("파일 확장자는 영문자로만 구성되어야 합니다.", BAD_REQUEST, "F_003");

    private final String message;
    private final HttpStatus status;
    private final String code;
}

