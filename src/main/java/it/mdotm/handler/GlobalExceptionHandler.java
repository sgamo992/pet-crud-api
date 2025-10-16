package it.mdotm.handler;

import it.mdotm.dto.BaseException;
import it.mdotm.exception.ElementNotFoundException;
import it.mdotm.exception.MissingArgumentException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<BaseException> handleValidationException(HttpServletRequest request, MethodArgumentNotValidException exception) {
        var errors = new StringBuilder();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.append(errorMessage).append(" # ");
        });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(BaseException.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(errors.toString())
                        .timestamp(System.currentTimeMillis())
                        .path(request.getRequestURI())
                        .build()
                );
    }

    @ExceptionHandler({ElementNotFoundException.class, MissingArgumentException.class})
    public ResponseEntity<BaseException> handleCustomException(HttpServletRequest request, Exception exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(BaseException.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(exception.getMessage())
                        .timestamp(System.currentTimeMillis())
                        .path(request.getRequestURI())
                        .build()
                );
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<BaseException> handleUncoveredException(HttpServletRequest request, Exception exception) {
        log.error("Uncovered exception ", exception);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(BaseException.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(exception.getMessage())
                        .timestamp(System.currentTimeMillis())
                        .path(request.getRequestURI())
                        .build()
                );
    }
}