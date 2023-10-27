package wdabookstore.bookstoremanager.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import wdabookstore.bookstoremanager.exceptions.error_message.ErrorMessageBuilder;
import wdabookstore.bookstoremanager.exceptions.error_message.ProblemType;
import wdabookstore.bookstoremanager.exceptions.validation_exceptions.FinalizeErrorException;
import wdabookstore.bookstoremanager.exceptions.validation_exceptions.InvalidDateException;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
@ControllerAdvice
public class FinalizeErrorExceptionHandler {
    @ExceptionHandler(FinalizeErrorException.class)
    public ResponseEntity<Object> handleEntityDeleteErrorException(Exception ex, WebRequest request) {
        List<String> objectProblems = Collections.emptyList();
        return ErrorMessageBuilder.buildErrorResponseEntity(HttpStatus.BAD_REQUEST,
                ErrorMessageBuilder.buildProblem(HttpStatus.BAD_REQUEST, ProblemType.RENTAL_FINISH_ERROR, ex.getMessage(), objectProblems));
    }
}
