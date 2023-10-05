package wdabookstore.bookstoremanager.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import wdabookstore.bookstoremanager.exceptions.error_message.ErrorMessageBuilder;
import wdabookstore.bookstoremanager.exceptions.error_message.ProblemType;
import wdabookstore.bookstoremanager.exceptions.validation_exceptions.DeleteErrorException;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
@RestControllerAdvice
public class DeleteErrorExceptionHandler {
    @ExceptionHandler(DeleteErrorException.class)
    public ResponseEntity<Object> handleEntityDeleteErrorException(Exception ex, WebRequest request) {
        List<String> objectProblems = Collections.emptyList();
        return ErrorMessageBuilder.buildErrorResponseEntity(HttpStatus.NOT_FOUND,
                ErrorMessageBuilder.buildProblem(HttpStatus.NOT_FOUND, ProblemType.RESOURCE_NOT_FOUND, ex.getMessage(), objectProblems));
    }
}