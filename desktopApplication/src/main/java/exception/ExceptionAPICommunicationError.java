package exception;

public class ExceptionAPICommunicationError extends Exception{
    public ExceptionAPICommunicationError(String message){
        System.out.println("API Exception " + message);
    }
}
