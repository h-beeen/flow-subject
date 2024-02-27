package team.flow.upload.global.exception.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@RequiredArgsConstructor
public enum GlobalError implements ErrorCode {

	GLOBAL_NOT_FOUND("해당 요청에 관련된 데이터를 찾을 수 없습니다.", NOT_FOUND, "G_001"),
	INVALID_REQUEST_PARAM("올바르지 않은 요청 파라미터입니다", BAD_REQUEST, "G_002");

	private final String message;
	private final HttpStatus status;
	private final String code;
}
