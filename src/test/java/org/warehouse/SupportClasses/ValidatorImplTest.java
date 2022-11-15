package org.warehouse.SupportClasses;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.warehouse.AccountingDocs.Entity.Movement;
import org.warehouse.AccountingDocs.Entity.Receipt;
import org.warehouse.AccountingDocs.Entity.Selling;
import org.warehouse.AccountingDocs.Entity.WriteOff;
import org.warehouse.AccountingDocs.Repository.MovementRepository;
import org.warehouse.AccountingDocs.Repository.ReceiptRepository;
import org.warehouse.AccountingDocs.Repository.SellingRepository;
import org.warehouse.AccountingDocs.Repository.WriteOffRepository;
import org.warehouse.Items.DTO.ItemDTO;
import org.warehouse.SupportingClasses.Validator;
import org.warehouse.SupportingClasses.ValidatorImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidatorImplTest {

    @Mock
    ReceiptRepository receiptRepository;
    @Mock
    SellingRepository sellingRepository;
    @Mock
    WriteOffRepository writeOffRepository;
    @Mock
    MovementRepository movementRepository;

    Validator validator;

    @BeforeEach
    void setUp() {
        validator =
                new ValidatorImpl(
                        receiptRepository,
                        sellingRepository,
                        writeOffRepository,
                        movementRepository);
    }


    @Test
    @DisplayName("Should throw an exception when the amount is less or equal to 0")
    void checkItemsInDocWhenAmountIsLessOrEqualTo0ThenThrowException() {
        // Arrange
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setAmount(0);
        itemDTO.setDescription("Test");
        itemDTO.setSku("Test");

        // Assert
        assertThrows(
                RuntimeException.class,
                () -> validator.checkItemsInDoc(List.of(itemDTO), "Receipt"));
    }

    @Test
    @DisplayName("Should throw an exception when the sku is empty")
    void checkItemsInDocWhenSkuIsEmptyThenThrowException() {
        // Arrange
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setSku("");
        itemDTO.setDescription("description");
        itemDTO.setAmount(1);

        // Assert
        assertThrows(
                RuntimeException.class,
                () -> validator.checkItemsInDoc(List.of(itemDTO), "Receipt"));
    }

    @Test
    @DisplayName("Should throw an exception when in receipt is unnecessary last sell price")
    void checkItemsInDocWhenUnnecessaryLastSellPriceThenThrowException() {
        // Arrange
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setDescription("Description");
        itemDTO.setSku("sku");
        itemDTO.setAmount(1);
        itemDTO.setLastSellPrice(120.0);

        // Assert
        assertThrows(
                RuntimeException.class,
                () -> validator.checkItemsInDoc(List.of(itemDTO), "Receipt"));
    }

    @Test
    @DisplayName("Should throw an exception when in receipt is unnecessary last buy price")
    void checkItemsInDocWhenUnnecessaryLastBuyPriceThenThrowException() {
        // Arrange
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setDescription("Description");
        itemDTO.setSku("sku");
        itemDTO.setAmount(1);
        itemDTO.setLastSellPrice(120.0);
        itemDTO.setLastBuyPrice(120.0);

        // Assert
        assertThrows(
                RuntimeException.class,
                () -> validator.checkItemsInDoc(List.of(itemDTO), "Selling"));
    }

    @Test
    @DisplayName("Should throw an exception when in receipt is unnecessary last buy and sell prices")
    void checkItemsInDocWhenUnnecessaryLastBuyAndSellPricesThenThrowException() {
        // Arrange
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setDescription("Description");
        itemDTO.setSku("sku");
        itemDTO.setAmount(1);
        itemDTO.setLastSellPrice(120.0);
        itemDTO.setLastBuyPrice(120.0);

        // Assert
        assertThrows(
                RuntimeException.class,
                () -> validator.checkItemsInDoc(List.of(itemDTO), "Movement"));
    }

    @Test
    @DisplayName("Should throw an exception when the sku is more than 10 characters")
    void checkItemsInDocWhenSkuIsMoreThan10CharactersThenThrowException() {
        // Arrange
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setSku("12345678901");
        itemDTO.setDescription("Test");
        itemDTO.setAmount(1);
        // Assert
        assertThrows(
                RuntimeException.class,
                () -> validator.checkItemsInDoc(List.of(itemDTO), "Receipt"));
    }

    @Test
    @DisplayName("Should throw an exception when the sku is less than 3 characters")
    void checkItemsInDocWhenSkuIsLessThan3CharactersThenThrowException() {
        // Arrange
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setSku("12");
        itemDTO.setDescription("Test");
        itemDTO.setAmount(1);

        // Assert
        assertThrows(
                RuntimeException.class,
                () -> validator.checkItemsInDoc(List.of(itemDTO), "Receipt"));
    }

    @Test
    @DisplayName("Should throw an exception when the doc number is already exists")
    void checkDocNumberWhenDocNumberIsAlreadyExistsThenThrowException() {
        // Act
        when(receiptRepository.findReceiptByNumber(1)).thenReturn(new Receipt());
        when(sellingRepository.findSellingByNumber(1)).thenReturn(new Selling());
        when(writeOffRepository.findWriteOffByNumber(1)).thenReturn(new WriteOff());
        when(movementRepository.findMovementByNumber(1)).thenReturn(new Movement());

        // Assert
        assertThrows(RuntimeException.class, () -> validator.checkDocNumber("Receipt", 1));
        assertThrows(RuntimeException.class, () -> validator.checkDocNumber("Selling", 1));
        assertThrows(RuntimeException.class, () -> validator.checkDocNumber("Write-off", 1));
        assertThrows(RuntimeException.class, () -> validator.checkDocNumber("Movement", 1));

        verify(receiptRepository, times(1)).findReceiptByNumber(1);
        verify(sellingRepository, times(1)).findSellingByNumber(1);
        verify(writeOffRepository, times(1)).findWriteOffByNumber(1);
        verify(movementRepository, times(1)).findMovementByNumber(1);
    }
}