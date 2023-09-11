package wdabookstore.bookstoremanager.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class BookstoreExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException exception){
    return buildResponseEntity(
            HttpStatus.NOT_FOUND,
            exception.getMessage(),
            Collections.singletonList(exception.getMessage()));
    }
    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<Object> handleEntityExistsException(EntityExistsException exception){
        return buildResponseEntity(
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                Collections.singletonList(exception.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> errors.add(
                fieldError.getField().toUpperCase() +  ": " + fieldError.getDefaultMessage())
        );
        exception.getBindingResult().getGlobalErrors().forEach(globalErrors -> errors.add(
                "Object " + globalErrors.getObjectName() +  " " + globalErrors.getDefaultMessage())
        );
        return buildResponseEntity(HttpStatus.BAD_REQUEST, "Erro de validação dos argumentos Informados", errors);
    }
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST,
                "malformed JSON body and/or field error",
                Collections.singletonList(exception.getLocalizedMessage()));
    }
    private ResponseEntity<Object> buildResponseEntity(HttpStatus httpStatus, String message, List<String> errors) {
        ApiError apiError = ApiError.builder()

                .message(message)
                .errors(errors)
                .build();
        return ResponseEntity.status(httpStatus).body(apiError);
    }

}
