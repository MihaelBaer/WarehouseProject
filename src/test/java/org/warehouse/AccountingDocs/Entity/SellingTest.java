package org.warehouse.AccountingDocs.Entity;

import org.junit.jupiter.api.Test;
import org.warehouse.Warehouses.Entity.Warehouse;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SellingTest {

    /**
     * Method under test: {@link Selling#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        Selling selling = new Selling();
        selling.setId(123L);
        selling.setItemSet(new HashSet<>());
        selling.setNumber(10);
        selling.setSellingDate(LocalDate.ofEpochDay(1L));
        selling.setWarehouse(warehouse);
        Selling o = null;

        // Act
        boolean actualEqualsResult = selling.equals(o);

        // Assert
        assertNotEquals(selling, o);
    }

    /**
     * Method under test: {@link Selling#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        Selling selling = new Selling();
        selling.setId(123L);
        selling.setItemSet(new HashSet<>());
        selling.setNumber(10);
        selling.setSellingDate(LocalDate.ofEpochDay(1L));
        selling.setWarehouse(warehouse);
        String o = "Different type to Selling";

        // Act
        boolean actualEqualsResult = selling.equals(o);

        // Assert
        assertNotEquals(selling, o);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Selling#equals(Object)}
     *   <li>{@link Selling#hashCode()}
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

        Selling selling = new Selling();
        selling.setId(123L);
        selling.setItemSet(new HashSet<>());
        selling.setNumber(10);
        selling.setSellingDate(LocalDate.ofEpochDay(1L));
        selling.setWarehouse(warehouse);

        // Act
        boolean actualEqualsResult = selling.equals(selling);

        // Assert
        assertEquals(selling, selling);
        int expectedHashCodeResult = selling.hashCode();
        assertEquals(expectedHashCodeResult, selling.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Selling#equals(Object)}
     *   <li>{@link Selling#hashCode()}
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

        Selling selling = new Selling();
        selling.setId(123L);
        selling.setItemSet(new HashSet<>());
        selling.setNumber(10);
        selling.setSellingDate(LocalDate.ofEpochDay(1L));
        selling.setWarehouse(warehouse);

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setId(123L);
        warehouse1.setItemSet(new HashSet<>());
        warehouse1.setName("Name");
        warehouse1.setSellingSet(new HashSet<>());

        Selling selling1 = new Selling();
        selling1.setId(123L);
        selling1.setItemSet(new HashSet<>());
        selling1.setNumber(10);
        selling1.setSellingDate(LocalDate.ofEpochDay(1L));
        selling1.setWarehouse(warehouse1);

        // Act
        boolean actualEqualsResult = selling.equals(selling1);

        // Assert
        assertEquals(selling, selling1);
        int expectedHashCodeResult = selling.hashCode();
        assertEquals(expectedHashCodeResult, selling1.hashCode());
    }

    /**
     * Method under test: {@link Selling#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setMovementSet(new HashSet<>());

        Selling selling = new Selling();
        selling.setId(1L);
        selling.setItemSet(new HashSet<>());
        selling.setNumber(10);
        selling.setSellingDate(LocalDate.ofEpochDay(1L));
        selling.setWarehouse(warehouse);

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setId(123L);
        warehouse1.setItemSet(new HashSet<>());
        warehouse1.setName("Name");
        warehouse1.setWriteOffSet(new HashSet<>());

        Selling selling1 = new Selling();
        selling1.setId(123L);
        selling1.setItemSet(new HashSet<>());
        selling1.setNumber(10);
        selling1.setSellingDate(LocalDate.ofEpochDay(1L));
        selling1.setWarehouse(warehouse1);

        // Act
        boolean actualEqualsResult = selling.equals(selling1);

        // Assert
        assertNotEquals(selling, selling1);
    }

    /**
     * Method under test: {@link Selling#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        Selling selling = new Selling();
        selling.setId(null);
        selling.setItemSet(new HashSet<>());
        selling.setNumber(10);
        selling.setSellingDate(LocalDate.ofEpochDay(1L));
        selling.setWarehouse(warehouse);

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setId(123L);
        warehouse1.setItemSet(new HashSet<>());
        warehouse1.setName("Name");
        warehouse1.setReceiptSet(new HashSet<>());

        Selling selling1 = new Selling();
        selling1.setId(123L);
        selling1.setItemSet(new HashSet<>());
        selling1.setNumber(10);
        selling1.setSellingDate(LocalDate.ofEpochDay(1L));
        selling1.setWarehouse(warehouse1);

        // Act
        boolean actualEqualsResult = selling.equals(selling1);

        // Assert
        assertNotEquals(selling, selling1);
    }
}

