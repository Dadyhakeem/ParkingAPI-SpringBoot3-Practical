package dev.Hakeem.parkingapi_springboot3_practical.exception;

public class UsernameUniqueViolationException extends RuntimeException {
        public UsernameUniqueViolationException(String message){
            super(message);
        }
}
