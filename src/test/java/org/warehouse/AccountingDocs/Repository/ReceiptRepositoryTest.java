package org.warehouse.AccountingDocs.Repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.warehouse.AccountingDocs.Entity.Receipt;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReceiptRepositoryTest {

    @Mock
    private ReceiptRepository receiptRepository;

    @Test
    @DisplayName("Should return null when the receipt is not found")
    void findReceiptByNumberWhenReceiptIsNotFoundThenReturnNull() {
        // Arrange
        Integer number = 1;
        Receipt receipt = new Receipt(1L, number, LocalDate.now(), null, null);

        // Act
        Receipt result = receiptRepository.findReceiptByNumber(2);

        // Assert
        assertNull(result);
    }

    @Test
    @DisplayName("Should return the receipt when the receipt is found")
    void findReceiptByNumberWhenReceiptIsFoundThenReturnTheReceipt() {
        // Arrange
        Receipt receipt = new Receipt(1L, 1, LocalDate.now(), null, null);

        // Act
        when(receiptRepository.findReceiptByNumber(1)).thenReturn(receipt);
        Receipt foundReceipt = receiptRepository.findReceiptByNumber(1);

        // Assert
        assertEquals(receipt, foundReceipt);
    }
}