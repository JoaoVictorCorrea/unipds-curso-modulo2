package br.com.projectspringmvc.events.controller;

import br.com.projectspringmvc.events.dto.ErrorDTO;
import br.com.projectspringmvc.events.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(exception = NotFoundException.class)
    public ResponseEntity<ErrorDTO> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(400).body(new ErrorDTO(ex.getMessage()));
    }
}
