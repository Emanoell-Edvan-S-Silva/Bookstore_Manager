package wdabookstore.bookstoremanager.exceptions.validation_exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ExistingFieldException extends RuntimeException{
    public ExistingFieldException(String message){
        super(message);
    }
}
