package com.example.LibraryVol2.configuration;

import com.example.LibraryVol2.exceptions.ExceptionJSON;
import com.example.LibraryVol2.exceptions.FailAddingUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {
//
//    @ExceptionHandler(SQLException.class)
//    public String handleSQLException(HttpServletRequest request, Exception ex){
//        logger.info("SQLException Occured:: URL="+request.getRequestURL());
//        return "database_error";
//    }
//
//    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="IOException occured")
//    @ExceptionHandler(IOException.class)
//    public void handleIOException(){
//        logger.error("IOException handler executed");
//    }

    @ExceptionHandler(FailAddingUserException.class)
    public ResponseEntity<ExceptionJSON> handleFailAddingUserException(HttpServletRequest request, Exception ex){
        ExceptionJSON response = new ExceptionJSON(request.getRequestURL().toString(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
