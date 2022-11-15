package org.warehouse.SupportClasses;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.warehouse.SupportingClasses.GlobalExceptionHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    @DisplayName(
            "Should return a response with status code 406 and message error occurs:  + " +
                    "runtimeexception.getmessage() when the exception is RuntimeException")
    void handleRuntimeExceptionWhenTheExceptionIsRuntimeException() {
        // Arrange
        RuntimeException runtimeException = new RuntimeException("runtime exception");

        // Act
        ResponseEntity<String> responseEntity =
                globalExceptionHandler.handleRuntimeException(runtimeException);

        // Assert
        assertEquals(HttpStatus.NOT_ACCEPTABLE, responseEntity.getStatusCode());
        assertEquals("Error occurs: runtime exception", responseEntity.getBody());
    }

    @Test
    @DisplayName(
            "Should return a response with status code 406 when the exception is MethodArgumentNotValidException")
    void
    handleBlogAlreadyExistsException_WhenMethodArgumentNotValidException_ThenReturnResponseWithStatusCode406() {
        // Arrange
        MethodArgumentNotValidException methodArgumentNotValidException =
                mock(MethodArgumentNotValidException.class);
        FieldError fieldError = mock(FieldError.class);

        // Act
        when(methodArgumentNotValidException.getFieldError()).thenReturn(fieldError);
        when(fieldError.getField()).thenReturn("field");
        when(fieldError.getDefaultMessage()).thenReturn("default message");

        ResponseEntity<String> responseEntity =
                globalExceptionHandler.handleBlogAlreadyExistsException(
                        methodArgumentNotValidException);
        // Assert
        assertEquals(HttpStatus.NOT_ACCEPTABLE, responseEntity.getStatusCode());
    }
}