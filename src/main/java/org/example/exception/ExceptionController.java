package org.example.exception;

import org.example.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler (UsernameIsExistsException.class)
    public ResponseEntity<ResponseDto> userExistedHandler (UsernameIsExistsException e) {
        Map<String, Object> cause = new HashMap<>();
        cause.put("existedUsername", e.getUsernameExisted());
        ResponseDto response = new ResponseDto(e.getMessage(), cause);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler (EmailBeUsedException.class)
    public ResponseEntity<ResponseDto> emailBeUsedHandler (EmailBeUsedException e) {
        Map<String, Object> cause = new HashMap<>();
        cause.put("emailBeUsed", e.getEmailBeUsed());
        ResponseDto responseDto = new ResponseDto(e.getMessage(), cause);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseDto);
    }

    @ExceptionHandler (MethodArgumentNotValidException.class)
    @ResponseBody
    protected ResponseEntity <ResponseDto> handleMethodArgumentNotValid (MethodArgumentNotValidException exp) {
        Map<String, Object> errors = new HashMap<>();
        exp.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        ResponseDto responseDto = new ResponseDto("Method Argument Not Valid", errors);
        return new ResponseEntity<ResponseDto> (responseDto, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler (InputInvalidException.class)
    protected ResponseEntity<ResponseDto> handleInputInvalidation (InputInvalidException ex) {
        String message = ex.getMessage();
        Map<String, Object> errors = ex.getCauses();
        ResponseDto responseError = new ResponseDto(message, errors);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError);
    }
}
