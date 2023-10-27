package wdabookstore.bookstoremanager.exceptions.handlers;

import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import wdabookstore.bookstoremanager.exceptions.error_message.ErrorMessageBuilder;
import wdabookstore.bookstoremanager.exceptions.error_message.ProblemType;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@ControllerAdvice
public class GeneralBookstoreExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> errors.add(
                fieldError.getDefaultMessage())
        );
        exception.getBindingResult().getGlobalErrors().forEach(globalErrors -> errors.add(
                globalErrors.getDefaultMessage())
        );

        return ErrorMessageBuilder.buildErrorResponseEntity(HttpStatus.BAD_REQUEST,
                ErrorMessageBuilder.buildProblem(HttpStatus.BAD_REQUEST, ProblemType.INVALID_PARAMETER,
                        "Erro de Validação: Cheque o campo 'errors' para mais detalhes.", errors));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable mostSpecificCause = exception.getMostSpecificCause();
        List<String> errors = new ArrayList<>();
        if (mostSpecificCause instanceof JsonParseException) {
            JsonParseException jsonParseException = (JsonParseException) mostSpecificCause;
            errors.add("Erro na análise do JSON: " + jsonParseException.getOriginalMessage());
        }
        return ErrorMessageBuilder.buildErrorResponseEntity(HttpStatus.BAD_REQUEST,
                ErrorMessageBuilder.buildProblem(HttpStatus.BAD_REQUEST, ProblemType.MALFORMED_BODY,
                        "Corpo malformado e/ou erro nos campos", errors));
    }

}
