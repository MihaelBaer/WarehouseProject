package org.warehouse.Warehouses.Entity;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class WarehouseTest {
    /**
     * Method under test: {@link Warehouse#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setMovementSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());
        warehouse.setSellingSet(new HashSet<>());
        warehouse.setWriteOffSet(new HashSet<>());
        Warehouse o = null;

        // Act
        boolean actualEqualsResult = warehouse.equals(o);

        // Assert
        assertNotEquals(warehouse, o);
    }

    /**
     * Method under test: {@link Warehouse#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setMovementSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());
        warehouse.setSellingSet(new HashSet<>());
        warehouse.setWriteOffSet(new HashSet<>());
        String o = "Different type to Warehouse";

        // Act
        boolean actualEqualsResult = warehouse.equals(o);

        // Assert
        assertNotEquals(warehouse, o);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Warehouse#equals(Object)}
     *   <li>{@link Warehouse#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setMovementSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());
        warehouse.setSellingSet(new HashSet<>());
        warehouse.setWriteOffSet(new HashSet<>());

        // Act
        boolean actualEqualsResult = warehouse.equals(warehouse);

        // Assert
        assertEquals(warehouse, warehouse);
        int expectedHashCodeResult = warehouse.hashCode();
        assertEquals(expectedHashCodeResult, warehouse.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Warehouse#equals(Object)}
     *   <li>{@link Warehouse#hashCode()}
     * </ul>
     */
    @Test
    void testEquals4() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setMovementSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());
        warehouse.setSellingSet(new HashSet<>());
        warehouse.setWriteOffSet(new HashSet<>());

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setId(123L);
        warehouse1.setItemSet(new HashSet<>());
        warehouse1.setMovementSet(new HashSet<>());
        warehouse1.setName("Name");
        warehouse1.setReceiptSet(new HashSet<>());
        warehouse1.setSellingSet(new HashSet<>());
        warehouse1.setWriteOffSet(new HashSet<>());

        // Act
        boolean actualEqualsResult = warehouse.equals(warehouse1);

        // Assert
        assertEquals(warehouse, warehouse1);
        int expectedHashCodeResult = warehouse.hashCode();
        assertEquals(expectedHashCodeResult, warehouse1.hashCode());
    }
}

