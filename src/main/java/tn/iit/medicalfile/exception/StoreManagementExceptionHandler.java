package tn.iit.medicalfile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StoreManagementExceptionHandler {

    @ExceptionHandler(tn.iit.medicalfile.exception.ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorSM> handleRessourceNotFound(tn.iit.medicalfile.exception.ResourceNotFoundException exception){
        ResponseEntity.BodyBuilder builder = ResponseEntity.status (HttpStatus.NOT_FOUND);
        return builder.body (new ErrorSM ("NOT FOUND", exception.getMessage ()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorSM> handleValidationError(MethodArgumentNotValidException exception){
        ResponseEntity.BodyBuilder builder = ResponseEntity.status (HttpStatus.BAD_REQUEST);
        return builder.body (new ErrorSM ("VALIDATION ERROR", exception.getMessage ()));
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResponseEntity<ErrorSM> handleException(Exception exception){
//        ResponseEntity.BodyBuilder builder = ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR);
//        return builder.body (new ErrorSM ("Internal Server Error", exception.getMessage ()));
//    }

}
