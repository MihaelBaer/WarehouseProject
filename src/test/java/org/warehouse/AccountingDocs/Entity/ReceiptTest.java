package org.warehouse.AccountingDocs.Entity;

import org.junit.jupiter.api.Test;
import org.warehouse.Warehouses.Entity.Warehouse;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ReceiptTest {

    /**
     * Method under test: {@link Receipt#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        Receipt receipt = new Receipt();
        receipt.setId(123L);
        receipt.setItemSet(new HashSet<>());
        receipt.setNumber(10);
        receipt.setReceiptDate(LocalDate.ofEpochDay(1L));
        receipt.setWarehouse(warehouse);
        Receipt o = null;

        // Act
        boolean actualEqualsResult = receipt.equals(o);

        // Assert
        assertNotEquals(receipt, o);
    }

    /**
     * Method under test: {@link Receipt#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        Receipt receipt = new Receipt();
        receipt.setId(123L);
        receipt.setItemSet(new HashSet<>());
        receipt.setNumber(10);
        receipt.setReceiptDate(LocalDate.ofEpochDay(1L));
        receipt.setWarehouse(warehouse);
        String o = "Different type to Receipt";

        // Act
        boolean actualEqualsResult = receipt.equals(o);

        // Assert
        assertNotEquals(receipt, o);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Receipt#equals(Object)}
     *   <li>{@link Receipt#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        Receipt receipt = new Receipt();
        receipt.setId(123L);
        receipt.setItemSet(new HashSet<>());
        receipt.setNumber(10);
        receipt.setReceiptDate(LocalDate.ofEpochDay(1L));
        receipt.setWarehouse(warehouse);

        // Act
        boolean actualEqualsResult = receipt.equals(receipt);

        // Assert
        assertEquals(receipt, receipt);
        int expectedHashCodeResult = receipt.hashCode();
        assertEquals(expectedHashCodeResult, receipt.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Receipt#equals(Object)}
     *   <li>{@link Receipt#hashCode()}
     * </ul>
     */
    @Test
    void testEquals4() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        Receipt receipt = new Receipt();
        receipt.setId(123L);
        receipt.setItemSet(new HashSet<>());
        receipt.setNumber(10);
        receipt.setReceiptDate(LocalDate.ofEpochDay(1L));
        receipt.setWarehouse(warehouse);

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse1.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        Receipt receipt1 = new Receipt();
        receipt1.setId(123L);
        receipt1.setItemSet(new HashSet<>());
        receipt1.setNumber(10);
        receipt1.setReceiptDate(LocalDate.ofEpochDay(1L));
        receipt1.setWarehouse(warehouse1);

        // Act
        boolean actualEqualsResult = receipt.equals(receipt1);

        // Assert
        assertEquals(receipt, receipt1);
        int expectedHashCodeResult = receipt.hashCode();
        assertEquals(expectedHashCodeResult, receipt1.hashCode());
    }

    /**
     * Method under test: {@link Receipt#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        Receipt receipt = new Receipt();
        receipt.setId(1L);
        receipt.setItemSet(new HashSet<>());
        receipt.setNumber(10);
        receipt.setReceiptDate(LocalDate.ofEpochDay(1L));
        receipt.setWarehouse(warehouse);

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse1.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        Receipt receipt1 = new Receipt();
        receipt1.setId(123L);
        receipt1.setItemSet(new HashSet<>());
        receipt1.setNumber(10);
        receipt1.setReceiptDate(LocalDate.ofEpochDay(1L));
        receipt1.setWarehouse(warehouse1);

        // Act
        boolean actualEqualsResult = receipt.equals(receipt1);

        // Assert
        assertNotEquals(receipt, receipt1);
    }

    /**
     * Method under test: {@link Receipt#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setItemSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        Receipt receipt = new Receipt();
        receipt.setId(null);
        receipt.setItemSet(new HashSet<>());
        receipt.setNumber(10);
        receipt.setReceiptDate(LocalDate.ofEpochDay(1L));
        receipt.setWarehouse(warehouse);

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse1.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        Receipt receipt1 = new Receipt();
        receipt1.setId(123L);
        receipt1.setItemSet(new HashSet<>());
        receipt1.setNumber(10);
        receipt1.setReceiptDate(LocalDate.ofEpochDay(1L));
        receipt1.setWarehouse(warehouse1);

        // Act
        boolean actualEqualsResult = receipt.equals(receipt1);

        // Assert
        assertNotEquals(receipt, receipt1);
    }
}

