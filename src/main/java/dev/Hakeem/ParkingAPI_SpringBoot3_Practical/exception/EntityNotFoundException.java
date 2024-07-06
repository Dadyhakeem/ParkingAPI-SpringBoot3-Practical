package dev.hakeem.parkingapi_springboot3_practical.exception;

public class EntityNotFoundException extends RuntimeException {
         public EntityNotFoundException(String message){
            super(message);
         }
}
