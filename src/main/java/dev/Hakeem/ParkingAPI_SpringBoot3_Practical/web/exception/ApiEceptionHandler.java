package dev.hakeem.parkingapi_springboot3_practical.web.exception;


import dev.hakeem.parkingapi_springboot3_practical.exception.PasswordInvalidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.hakeem.parkingapi_springboot3_practical.exception.EntityNotFoundException;
import dev.hakeem.parkingapi_springboot3_practical.exception.UsernameUniqueViolationException;
import jakarta.servlet.http.HttpServletRequest;
@Slf4j
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

    @ExceptionHandler(PasswordInvalidException.class)
    public ResponseEntity<ErrorMessage> passwordInvalidException(RuntimeException ex, HttpServletRequest request) {
        log.error("Api Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, ex.getMessage()));
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorMessage> AccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
        log.error("Api Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.FORBIDDEN, ex.getMessage()));
    }
}
