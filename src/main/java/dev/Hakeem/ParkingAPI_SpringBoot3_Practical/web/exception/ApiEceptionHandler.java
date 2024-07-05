package dev.Hakeem.parkingapi_springboot3_practical.web.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.Hakeem.parkingapi_springboot3_practical.exception.EntityNotFoundException;
import dev.Hakeem.parkingapi_springboot3_practical.exception.UsernameUniqueViolationException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiEceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage>methodArgumentNotValidException                (MethodArgumentNotValidException  ex,HttpServletRequest  request,BindingResult result)
    {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).contentType(MediaType.APPLICATION_JSON).body(new ErrorMessage(request,HttpStatus.UNPROCESSABLE_ENTITY,"Campos(s) invalido(s)",result));
    }
    
    
    @ExceptionHandler(UsernameUniqueViolationException.class)
    public ResponseEntity<ErrorMessage>usernameUniqueViolationException(RuntimeException  ex,HttpServletRequest  request)
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).contentType(MediaType.APPLICATION_JSON).body(new ErrorMessage(request,HttpStatus.CONFLICT,ex.getMessage()));
    }
    
    
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage>entityNotFoundException(RuntimeException  ex,HttpServletRequest  request)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(new ErrorMessage(request,HttpStatus.NOT_FOUND,ex.getMessage()));
    }
}