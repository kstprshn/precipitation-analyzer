package com.example.project3.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Component
public class ErrorsUtil {

    public  void returnErrorsToClient(BindingResult bindingResult){

        var errorMessage = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for(FieldError er: errors){
            errorMessage.append(er.getField())
                    .append(" - ")
                    .append(er.getDefaultMessage() == null ? er.getCode() : er.getDefaultMessage())
                    .append(";");
        }
        throw new MeasurementException(errorMessage.toString());
    }
}
