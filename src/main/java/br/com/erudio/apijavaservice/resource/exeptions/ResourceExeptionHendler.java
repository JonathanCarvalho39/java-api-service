package br.com.erudio.apijavaservice.resource.exeptions;

import br.com.erudio.apijavaservice.services.exeptions.ObjectNotFoundExeption;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExeptionHendler {

    @ExceptionHandler(ObjectNotFoundExeption.class)
    public ResponseEntity<StanderdError> objectNotFoundExeption(
            ObjectNotFoundExeption ex,
            HttpServletRequest request) {

        StanderdError error = new StanderdError(
                System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(),
                "Object Not Found",
                ex.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StanderdError> objectNotFoundExeption(
            DataIntegrityViolationException ex,
            HttpServletRequest request) {

        StanderdError error = new StanderdError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Violação de dados",
                ex.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
