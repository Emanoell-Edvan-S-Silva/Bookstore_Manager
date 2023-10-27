package wdabookstore.bookstoremanager.exceptions.validation_exceptions;

public class FinalizeErrorException extends RuntimeException{
    public FinalizeErrorException(String message){
        super(message);
    }
}
