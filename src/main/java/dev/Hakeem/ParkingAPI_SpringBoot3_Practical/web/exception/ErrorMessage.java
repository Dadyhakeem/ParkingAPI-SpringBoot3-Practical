package dev.hakeem.parkingapi_springboot3_practical.web.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.ToString;



@ToString
@Getter
public class ErrorMessage {
    
    
    private String path;
    private String method;
    private int status;
    private String statustext;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String,String>erros;

    
    
    public ErrorMessage() {
    }


    public ErrorMessage(HttpServletRequest request,HttpStatus status,String message) {
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.statustext = status.getReasonPhrase();
        this.message = message;
    }
    public ErrorMessage(HttpServletRequest request,HttpStatus status,String message,BindingResult result) {
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.statustext = status.getReasonPhrase();
        this.message = message;
        addErros(result);
    }
    private void addErros(BindingResult result) {
        this.erros = new HashMap<>();
        for (FieldError fieldError : result.getFieldErrors()) {
            this.erros.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }




    






}
