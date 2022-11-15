package org.warehouse.AccountingDocs.Entity;

import org.junit.jupiter.api.Test;
import org.warehouse.Warehouses.Entity.Warehouse;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class WriteOffTest {

    /**
    * Method under test: {@link WriteOff#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        WriteOff writeOff = new WriteOff();
        writeOff.setId(123L);
        writeOff.setItemSet(new HashSet<>());
        writeOff.setNumber(10);
        writeOff.setWriteOffDate(LocalDate.ofEpochDay(1L));
        writeOff.setWarehouse(warehouse);
        WriteOff o = null;

        // Act
        boolean actualEqualsResult = writeOff.equals(o);

        // Assert
        assertNotEquals(writeOff, o);
    }

    /**
     * Method under test: {@link WriteOff#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        WriteOff writeOff = new WriteOff();
        writeOff.setId(123L);
        writeOff.setItemSet(new HashSet<>());
        writeOff.setNumber(10);
        writeOff.setWriteOffDate(LocalDate.ofEpochDay(1L));
        writeOff.setWarehouse(warehouse);
        String o = "Different type to WriteOff";

        // Act
        boolean actualEqualsResult = writeOff.equals(o);

        // Assert
        assertNotEquals(writeOff, o);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link WriteOff#equals(Object)}
     *   <li>{@link WriteOff#hashCode()}
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

        WriteOff writeOff = new WriteOff();
        writeOff.setId(123L);
        writeOff.setItemSet(new HashSet<>());
        writeOff.setNumber(10);
        writeOff.setWriteOffDate(LocalDate.ofEpochDay(1L));
        writeOff.setWarehouse(warehouse);

        // Act
        boolean actualEqualsResult = writeOff.equals(writeOff);

        // Assert
        assertEquals(writeOff, writeOff);
        int expectedHashCodeResult = writeOff.hashCode();
        assertEquals(expectedHashCodeResult, writeOff.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link WriteOff#equals(Object)}
     *   <li>{@link WriteOff#hashCode()}
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

        WriteOff writeOff = new WriteOff();
        writeOff.setId(123L);
        writeOff.setItemSet(new HashSet<>());
        writeOff.setNumber(10);
        writeOff.setWriteOffDate(LocalDate.ofEpochDay(1L));
        writeOff.setWarehouse(warehouse);

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setId(123L);
        warehouse1.setItemSet(new HashSet<>());
        warehouse1.setName("Name");
        warehouse1.setWriteOffSet(new HashSet<>());

        WriteOff writeOff1 = new WriteOff();
        writeOff1.setId(123L);
        writeOff1.setItemSet(new HashSet<>());
        writeOff1.setNumber(10);
        writeOff1.setWriteOffDate(LocalDate.ofEpochDay(1L));
        writeOff1.setWarehouse(warehouse1);

        // Act
        boolean actualEqualsResult = writeOff.equals(writeOff1);

        // Assert
        assertEquals(writeOff, writeOff1);
        int expectedHashCodeResult = writeOff.hashCode();
        assertEquals(expectedHashCodeResult, writeOff1.hashCode());
    }

    /**
     * Method under test: {@link WriteOff#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setMovementSet(new HashSet<>());

        WriteOff writeOff = new WriteOff();
        writeOff.setId(1L);
        writeOff.setItemSet(new HashSet<>());
        writeOff.setNumber(10);
        writeOff.setWriteOffDate(LocalDate.ofEpochDay(1L));
        writeOff.setWarehouse(warehouse);

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setId(123L);
        warehouse1.setItemSet(new HashSet<>());
        warehouse1.setName("Name");
        warehouse1.setWriteOffSet(new HashSet<>());

        WriteOff writeOff1 = new WriteOff();
        writeOff1.setId(123L);
        writeOff1.setItemSet(new HashSet<>());
        writeOff1.setNumber(10);
        writeOff1.setWriteOffDate(LocalDate.ofEpochDay(1L));
        writeOff1.setWarehouse(warehouse1);

        // Act
        boolean actualEqualsResult = writeOff.equals(writeOff1);

        // Assert
        assertNotEquals(writeOff, writeOff1);
    }

    /**
     * Method under test: {@link WriteOff#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        WriteOff writeOff = new WriteOff();
        writeOff.setId(null);
        writeOff.setItemSet(new HashSet<>());
        writeOff.setNumber(10);
        writeOff.setWriteOffDate(LocalDate.ofEpochDay(1L));
        writeOff.setWarehouse(warehouse);

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setId(123L);
        warehouse1.setItemSet(new HashSet<>());
        warehouse1.setName("Name");
        warehouse1.setReceiptSet(new HashSet<>());

        WriteOff writeOff1 = new WriteOff();
        writeOff1.setId(123L);
        writeOff1.setItemSet(new HashSet<>());
        writeOff1.setNumber(10);
        writeOff1.setWriteOffDate(LocalDate.ofEpochDay(1L));
        writeOff1.setWarehouse(warehouse1);

        // Act
        boolean actualEqualsResult = writeOff.equals(writeOff1);

        // Assert
        assertNotEquals(writeOff, writeOff1);
    }

}