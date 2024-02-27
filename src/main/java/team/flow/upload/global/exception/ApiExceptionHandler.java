package team.flow.upload.global.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team.flow.upload.global.exception.error.ErrorCode;
import team.flow.upload.global.exception.error.ErrorResponse;

import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ErrorResponse.of(Optional.ofNullable(exception.getFieldError()));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleCommerceException(BusinessException exception) {
        logBusinessException(exception);
        return convert(exception.getErrorCode());
    }

    private ResponseEntity<ErrorResponse> convert(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getStatus())
                .body(ErrorResponse.of(errorCode));
    }

    private void logBusinessException(BusinessException exception) {
        if (exception.getErrorCode().getStatus().is5xxServerError()) {
            log.error("", exception);
        } else {
            log.error("error message = {}", exception.getMessage());
        }
    }
}

