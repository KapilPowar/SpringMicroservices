package com.kapil.restapi.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
        ResourceNotFoundException exception, WebRequest webRequest){

            ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "USER_NOT_FOUND");
            
                return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
        }

    @ExceptionHandler(EmailExistException.class)
    public ResponseEntity<ErrorDetails> handleEmailExistException(
        EmailExistException exception, WebRequest webRequest){
            ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "USER_EMAIL_ALREADY_EXISTS");
            return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
        }
    
        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorDetails> handleGlobalException( Exception ex, WebRequest webRequest){
            ErrorDetails errorDetails= new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                webRequest.getDescription(false),
                "INTERNAL SERVER ERROR");
            return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        //@ExceptionHandler(MethodArgumentNotValidException.class)
       @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
                                        HttpHeaders headers, HttpStatusCode status, WebRequest webRequest){
            Map<String, String> errors= new HashMap<>();
            List<ObjectError> errorList= ex.getBindingResult().getAllErrors(); 
            
            errorList.forEach((error)-> {
                String fieldName = ((FieldError)error).getField();
                String message = error.getDefaultMessage();
                errors.put(fieldName, "TEST");
            });
            return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);

        }

    
}
