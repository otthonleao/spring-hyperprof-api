package dev.otthon.hyperprof.api.common.handlers;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import dev.otthon.hyperprof.api.common.dtos.ErrorResponse;
import dev.otthon.hyperprof.api.common.dtos.ValidationErrorResponse;
import dev.otthon.hyperprof.core.exceptions.ModelNotFoundException;
import dev.otthon.hyperprof.core.services.token.TokenServiceException;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private final PropertyNamingStrategies.SnakeCaseStrategy snakeCaseStrategy = new PropertyNamingStrategies.SnakeCaseStrategy();

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleModelNotFoundException(ModelNotFoundException exception, WebRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var body = ErrorResponse.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(exception.getLocalizedMessage())
                .cause(exception.getClass().getSimpleName())
                .build();
        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(TokenServiceException.class)
    public ResponseEntity<ErrorResponse> handleTokenServiceException(TokenServiceException exception, WebRequest request) {
        var status = HttpStatus.UNAUTHORIZED;
        var body = ErrorResponse.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(exception.getLocalizedMessage())
                .cause(exception.getClass().getSimpleName())
                .build();
        return new ResponseEntity<>(body, status);
    }

    @Nullable
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        var status = (HttpStatus) statusCode;
        var errors = new HashMap<String, List<String>>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            var fieldName = snakeCaseStrategy.translate(error.getObjectName());
            var errorMessage = error.getDefaultMessage();

            if (errors.containsKey(fieldName)) {
                errors.get(fieldName).add(errorMessage);
            } else {
                errors.put(fieldName, new ArrayList<String>(Arrays.asList(errorMessage)));
            }
        });

        var body = ValidationErrorResponse.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message("Validation Error")
                .cause(ex.getClass().getSimpleName())
                .timestamp(LocalDateTime.now())
                .errors(errors)
                .build();

        return new ResponseEntity<Object>(body, status);
    }
}
