package wdabookstore.bookstoremanager.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import wdabookstore.bookstoremanager.exceptions.error_message.ErrorMessageBuilder;
import wdabookstore.bookstoremanager.exceptions.error_message.ProblemType;
import wdabookstore.bookstoremanager.exceptions.validation_exceptions.ExistingFieldExceptions;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
@RestControllerAdvice
public class ExistingFieldExceptionHandler {

    @ExceptionHandler(ExistingFieldExceptions.class)
    public ResponseEntity<Object> handleEntityExistsException(ExistingFieldExceptions exception){
        List<String> objectProblems = Collections.singletonList(exception.getMessage());
        return ErrorMessageBuilder.buildErrorResponseEntity(HttpStatus.BAD_REQUEST,
                ErrorMessageBuilder.buildProblem(HttpStatus.BAD_REQUEST,
                        ProblemType.DUPLICATE_FIELD,
                        "Existe Campos se repetindo",
                        objectProblems));
    }
}
