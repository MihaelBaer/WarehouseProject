package org.warehouse.AccountingDocs.Repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.warehouse.AccountingDocs.Entity.Movement;
import org.warehouse.Warehouses.Entity.Warehouse;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovementRepositoryTest {

    @Mock
    private MovementRepository movementRepository;
    @Mock
    private Warehouse warehouseFrom;
    @Mock
    private Warehouse warehouseTo;

    @Test
    @DisplayName("Should return the movement when the number is found")
    void findMovementByNumberWhenNumberIsFound() {
        // Arrange
        Movement movement = new Movement(1L, 1, LocalDate.now(), warehouseFrom, warehouseTo, new HashSet<>());

        // Act
        when(movementRepository.findMovementByNumber(1)).thenReturn(movement);

        // Assert
        assertEquals(movement, movementRepository.findMovementByNumber(1));
    }

    @Test
    @DisplayName("Should return null when the number is not found")
    void findMovementByNumberWhenNumberIsNotFoundThenReturnNull() {
        // Arrange
        Movement movement = new Movement(1L, 1, LocalDate.now(), warehouseFrom, warehouseTo, new HashSet<>());

        // Assert
        assertNull(movementRepository.findMovementByNumber(2));
    }
}