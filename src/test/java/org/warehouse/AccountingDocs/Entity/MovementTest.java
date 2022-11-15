package org.warehouse.AccountingDocs.Entity;

import org.junit.jupiter.api.Test;
import org.warehouse.Warehouses.Entity.Warehouse;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MovementTest {

    /**
     * Method under test: {@link Movement#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse1.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        Movement movement = new Movement();
        movement.setId(123L);
        movement.setItemSet(new HashSet<>());
        movement.setMovementDate(LocalDate.ofEpochDay(1L));
        movement.setNumber(10);
        movement.setWarehouseFrom(warehouse);
        movement.setWarehouseTo(warehouse1);
        Movement o = null;

        // Act
        boolean actualEqualsResult = movement.equals(o);

        // Assert
        assertNotEquals(movement, o);
    }

    /**
     * Method under test: {@link Movement#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse1.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        Movement movement = new Movement();
        movement.setId(123L);
        movement.setItemSet(new HashSet<>());
        movement.setMovementDate(LocalDate.ofEpochDay(1L));
        movement.setNumber(10);
        movement.setWarehouseFrom(warehouse);
        movement.setWarehouseTo(warehouse1);
        String o = "Different type to Movement";

        // Act
        boolean actualEqualsResult = movement.equals(o);

        // Assert
        assertNotEquals(movement, o);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Movement#equals(Object)}
     *   <li>{@link Movement#hashCode()}
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

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse1.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        Movement movement = new Movement();
        movement.setId(123L);
        movement.setItemSet(new HashSet<>());
        movement.setMovementDate(LocalDate.ofEpochDay(1L));
        movement.setNumber(10);
        movement.setWarehouseFrom(warehouse);
        movement.setWarehouseTo(warehouse1);

        // Act
        boolean actualEqualsResult = movement.equals(movement);

        // Assert
        assertEquals(movement, movement);
        int expectedHashCodeResult = movement.hashCode();
        assertEquals(expectedHashCodeResult, movement.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Movement#equals(Object)}
     *   <li>{@link Movement#hashCode()}
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

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse1.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        Movement movement = new Movement();
        movement.setId(123L);
        movement.setItemSet(new HashSet<>());
        movement.setMovementDate(LocalDate.ofEpochDay(1L));
        movement.setNumber(10);
        movement.setWarehouseFrom(warehouse);
        movement.setWarehouseTo(warehouse1);

        Warehouse warehouse2 = new Warehouse();
        warehouse2.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse2.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        Warehouse warehouse3 = new Warehouse();
        warehouse3.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse3.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        Movement movement1 = new Movement();
        movement1.setId(123L);
        movement1.setItemSet(new HashSet<>());
        movement1.setMovementDate(LocalDate.ofEpochDay(1L));
        movement1.setNumber(10);
        movement1.setWarehouseFrom(warehouse2);
        movement1.setWarehouseTo(warehouse3);

        // Act
        boolean actualEqualsResult = movement.equals(movement1);

        // Assert
        assertEquals(movement, movement1);
        int expectedHashCodeResult = movement.hashCode();
        assertEquals(expectedHashCodeResult, movement1.hashCode());
    }

    /**
     * Method under test: {@link Movement#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse1.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        Movement movement = new Movement();
        movement.setId(1L);
        movement.setItemSet(new HashSet<>());
        movement.setMovementDate(LocalDate.ofEpochDay(1L));
        movement.setNumber(10);
        movement.setWarehouseFrom(warehouse);
        movement.setWarehouseTo(warehouse1);

        Warehouse warehouse2 = new Warehouse();
        warehouse2.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse2.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        Warehouse warehouse3 = new Warehouse();
        warehouse3.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse3.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        Movement movement1 = new Movement();
        movement1.setId(123L);
        movement1.setItemSet(new HashSet<>());
        movement1.setMovementDate(LocalDate.ofEpochDay(1L));
        movement1.setNumber(10);
        movement1.setWarehouseFrom(warehouse2);
        movement1.setWarehouseTo(warehouse3);

        // Act
        boolean actualEqualsResult = movement.equals(movement1);

        // Assert
        assertNotEquals(movement, movement1);
    }

    /**
     * Method under test: {@link Movement#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse1.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        Movement movement = new Movement();
        movement.setId(null);
        movement.setItemSet(new HashSet<>());
        movement.setMovementDate(LocalDate.ofEpochDay(1L));
        movement.setNumber(10);
        movement.setWarehouseFrom(warehouse);
        movement.setWarehouseTo(warehouse1);

        Warehouse warehouse2 = new Warehouse();
        warehouse2.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse2.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        Warehouse warehouse3 = new Warehouse();
        warehouse3.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse3.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());

        Movement movement1 = new Movement();
        movement1.setId(123L);
        movement1.setItemSet(new HashSet<>());
        movement1.setMovementDate(LocalDate.ofEpochDay(1L));
        movement1.setNumber(10);
        movement1.setWarehouseFrom(warehouse2);
        movement1.setWarehouseTo(warehouse3);

        // Act
        boolean actualEqualsResult = movement.equals(movement1);

        // Assert
        assertNotEquals(movement, movement1);
    }
}

