package wdabookstore.bookstoremanager.exceptions.validation_exceptions;

public class BusinessErrorException extends RuntimeException{
    public BusinessErrorException(String message){
        super(message);
    }
}
