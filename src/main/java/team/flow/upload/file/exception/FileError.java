package team.flow.upload.file.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import team.flow.upload.global.exception.error.ErrorCode;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;

@Getter
@RequiredArgsConstructor
public enum FileError implements ErrorCode {

    UPLOAD_FAILED("파일 업로드에 실패했습니다.", BAD_REQUEST, "FU_001"),
    EMPTY_FILE("빈 파일을 업로드할 수 없습니다.", BAD_REQUEST, "FU_002"),
    DUPLICATED_FILE("이미 같은 이름의 파일이 업로드되어 있습니다.", CONFLICT, "FU_003"),
    UNKNOWN_ERROR("해당 파일을 찾을 수 없습니다.", BAD_REQUEST, "FU_005");

    private final String message;
    private final HttpStatus status;
    private final String code;
    }

