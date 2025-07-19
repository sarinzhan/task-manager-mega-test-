package kg.kazbekov.megatesttask.controller;

import kg.kazbekov.megatesttask.dto.RestResponse;
import kg.kazbekov.megatesttask.exception.client.BaseClientException;
import kg.kazbekov.megatesttask.exception.server.BaseServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<RestResponse<String>> handleBaseException(BaseServerException ex){
        RestResponse<String> response = new RestResponse<>();
        response.setMessage(ex.getMessage());

        log.error("Internal server error.", ex);
        return ResponseEntity
                .internalServerError()
                .body(response);
    }

    @ExceptionHandler(BaseClientException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestResponse<String>> handleBaseException(BaseClientException ex){
        RestResponse<String> response = new RestResponse<>();
        response.setMessage(ex.getMessage());

        return ResponseEntity
                .badRequest()
                .body(response);
    }

}
