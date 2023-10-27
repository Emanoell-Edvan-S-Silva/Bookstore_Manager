package wdabookstore.bookstoremanager.exceptions.validation_exceptions;

public class InvalidDateException extends RuntimeException{

    public InvalidDateException(String message){
        super(message);
    }
}
