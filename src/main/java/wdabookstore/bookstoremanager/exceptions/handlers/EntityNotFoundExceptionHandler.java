package wdabookstore.bookstoremanager.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import wdabookstore.bookstoremanager.exceptions.error_message.ErrorMessageBuilder;
import wdabookstore.bookstoremanager.exceptions.error_message.ProblemType;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
@RestControllerAdvice
public class EntityNotFoundExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(Exception ex, WebRequest request) {
        return ErrorMessageBuilder.buildErrorResponseEntity(HttpStatus.NOT_FOUND,
                ErrorMessageBuilder.buildProblem(HttpStatus.NOT_FOUND, ProblemType.RESOURCE_NOT_FOUND, "Entidade não existe", Collections.singletonList(ex.getMessage())));
    }
}
