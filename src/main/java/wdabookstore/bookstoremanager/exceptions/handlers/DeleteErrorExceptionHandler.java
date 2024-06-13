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
        return ErrorMessageBuilder.buildErrorResponseEntity(HttpStatus.BAD_REQUEST,
                ErrorMessageBuilder.buildProblem(HttpStatus.BAD_REQUEST, ProblemType.RESOURCE_NOT_FOUND, "Ouve um erro ao deletar", Collections.singletonList(ex.getMessage())));
    }
}
