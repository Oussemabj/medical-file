package tn.iit.medicalfile.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorSM  {

    private String message;
    private String conflict;
}
