package team.flow.upload.file.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import team.flow.upload.global.exception.error.ErrorCode;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@RequiredArgsConstructor
public enum FileError implements ErrorCode {

    UPLOAD_FAILED("파일 업로드에 실패했습니다.", BAD_REQUEST, "FU_001"),
    EMPTY_FILE("빈 파일을 업로드할 수 없습니다.", BAD_REQUEST, "FU_002");

    private final String message;
    private final HttpStatus status;
    private final String code;
    }

