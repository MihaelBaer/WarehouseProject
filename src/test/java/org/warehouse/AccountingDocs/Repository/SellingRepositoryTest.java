package org.warehouse.AccountingDocs.Repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.warehouse.AccountingDocs.Entity.Receipt;
import org.warehouse.AccountingDocs.Entity.Selling;
import org.warehouse.Warehouses.Entity.Warehouse;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SellingRepositoryTest {

    @Mock
    private SellingRepository sellingRepository;

    @Test
    @DisplayName("Should return null when the selling is not found")
    void findSellingByNumberWhenSellingIsNotFound() {
        // Arrange
        Integer number = 1;
        Receipt receipt = new Receipt(1L, number, LocalDate.now(), null, null);

        // Act
        Selling result = sellingRepository.findSellingByNumber(2);

        // Assert
        assertNull(result);
    }

    @Test
    @DisplayName("Should return the selling when the selling is found")
    void findSellingByNumberWhenSellingIsFound() {
        // Arrange
        Selling selling = new Selling(1L, 1, LocalDate.now(), new Warehouse(), new HashSet<>());

        // Act
        when(sellingRepository.findSellingByNumber(1)).thenReturn(selling);
        Selling foundSelling = sellingRepository.findSellingByNumber(1);

        // Assert
        assertEquals(selling, foundSelling);
    }
}