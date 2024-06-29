package br.com.erudio.apijavaservice.resource.exeptions;

import br.com.erudio.apijavaservice.services.exeptions.ObjectNotFoundExeption;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StanderdError> validException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        ValidationError error = new ValidationError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Capos requeridos",
                "Error na validação dos campos",
                request.getRequestURI());

        for (FieldError x : ex.getBindingResult().getFieldErrors()) {
            error.addError(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StanderdError> validException(
            MethodArgumentTypeMismatchException ex,
            HttpServletRequest request) {

        ValidationError error = new ValidationError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Parametro invalido",
                "Erro, parametros invalidos",
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StanderdError> validException(
            ConstraintViolationException ex,
            HttpServletRequest request) {

        ValidationError error = new ValidationError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "CPF Inválido",
                "CPF Inválido",
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
