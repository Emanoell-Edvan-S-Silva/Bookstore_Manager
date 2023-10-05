package wdabookstore.bookstoremanager.exceptions.error_message;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;
import java.util.List;

public class ErrorMessageBuilder {

    public static ResponseEntity<Object> buildErrorResponseEntity(HttpStatus httpStatus, ErrorMessageModel errorMessageModel) {
        return ResponseEntity.status(httpStatus).body(errorMessageModel);
    }
    public static ErrorMessageModel buildProblem(HttpStatus status, ProblemType problemType, String detail, List<String> objectProblems) {
        return ErrorMessageModel.builder()
                .timestamp(OffsetDateTime.now())
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail)
                .errors(objectProblems)
                .build();
    }
}
