package org.warehouse.SupportClasses;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.warehouse.SupportingClasses.DateFormatterImpl;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class DateFormatterImplTest {

    @InjectMocks
    private DateFormatterImpl dateFormatter;

    @Test
    @DisplayName("Should throw an exception when the date to format is not in the correct format")
    void formatDateWhenDateToFormatIsNotCorrectThenThrowException() {
        // Arrange
        String dateToFormat = "01-Aug-2020";

        // Assert
        assertThrows(DateTimeParseException.class, () -> dateFormatter.formatDate(dateToFormat));
    }

    @Test
    @DisplayName("Should return a localdate when the date to format is in the correct format")
    void formatDateWhenDateToFormatIsCorrectThenReturnLocalDate() {
        // Arrange
        String dateToFormat = "01-01-2020";
        LocalDate expectedLocalDate = LocalDate.of(2020, 1, 1);

        // Act
        LocalDate actualLocalDate = dateFormatter.formatDate(dateToFormat);

        // Assert
        assertEquals(expectedLocalDate, actualLocalDate);
    }
}