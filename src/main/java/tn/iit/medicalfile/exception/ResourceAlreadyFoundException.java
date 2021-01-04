package tn.iit.medicalfile.exception;

public class ResourceAlreadyFoundException extends RuntimeException {

    public ResourceAlreadyFoundException(String message, Throwable cause)
    {
        super (message, cause);
    }
    public ResourceAlreadyFoundException(String message)
    {
        super (message);
    }
    public ResourceAlreadyFoundException(Throwable cause)
    {
        super (cause);
    }
}
