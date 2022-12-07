package org.warehouse.SupportingClasses;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Objects;

/**
 * Controller class, which handles multiple exception, which occurs while WarehouseApiController is working
 */
@ApiIgnore
@Hidden
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Method, handles runtime exceptions
     *
     * @param runtimeException - handled RuntimeException
     * @return - response entity with error message
     */
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException runtimeException) {
        return new ResponseEntity<>(
                "Error occurs: " + runtimeException.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * Method, handles not valid argument exceptions
     *
     * @param argException - handled MethodArgumentNotValidException exception
     * @return - response entity with error message
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleBlogAlreadyExistsException(MethodArgumentNotValidException argException) {
        return new ResponseEntity<>("Error occurs in field: "
                + Objects.requireNonNull(argException.getFieldError()).getField() + " ("
                + Objects.requireNonNull(argException.getFieldError()).getDefaultMessage() + ").",
                HttpStatus.NOT_ACCEPTABLE);
    }
}

