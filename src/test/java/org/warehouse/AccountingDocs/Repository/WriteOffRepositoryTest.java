package org.warehouse.AccountingDocs.Repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.warehouse.AccountingDocs.Entity.WriteOff;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WriteOffRepositoryTest {

    @Mock
    private WriteOffRepository writeOffRepository;

    @Test
    @DisplayName("Should return null when the write off does not exist")
    void findWriteOffByNumberWhenWriteOffDoesNotExistThenReturnNull() {
        // Act
        when(writeOffRepository.findWriteOffByNumber(1)).thenReturn(null);

        // Assert
        assertNull(writeOffRepository.findWriteOffByNumber(1));
    }

    @Test
    @DisplayName("Should return the write off when the write off exists")
    void findWriteOffByNumberWhenWriteOffExistsThenReturnTheWriteOff() {
        // Arrange
        WriteOff writeOff = new WriteOff(1L, 1, LocalDate.now(), null, null);

        // Act
        when(writeOffRepository.findWriteOffByNumber(1)).thenReturn(writeOff);

        // Assert
        assertEquals(writeOff, writeOffRepository.findWriteOffByNumber(1));
    }
}