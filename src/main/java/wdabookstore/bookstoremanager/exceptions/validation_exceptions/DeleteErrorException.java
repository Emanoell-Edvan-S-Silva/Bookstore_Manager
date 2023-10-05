package wdabookstore.bookstoremanager.exceptions.validation_exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DeleteErrorException extends RuntimeException{
    public DeleteErrorException(String message){
        super(message);
    }
}
