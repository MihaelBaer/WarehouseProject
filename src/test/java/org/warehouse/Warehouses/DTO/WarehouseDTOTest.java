package org.warehouse.Warehouses.DTO;

import org.junit.jupiter.api.Test;
import org.warehouse.Items.Entity.Item;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseDTOTest {
    /**
     * Method under test: {@link WarehouseDTO#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange
        WarehouseDTO warehouseDTO = new WarehouseDTO();
        String other = "Other";

        // Act
        boolean actualCanEqualResult = warehouseDTO.canEqual(other);

        // Assert
        assertFalse(actualCanEqualResult);
    }

    /**
     * Method under test: {@link WarehouseDTO#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        WarehouseDTO warehouseDTO = new WarehouseDTO();
        WarehouseDTO other = new WarehouseDTO();

        // Act
        boolean actualCanEqualResult = warehouseDTO.canEqual(other);

        // Assert
        assertTrue(actualCanEqualResult);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link WarehouseDTO#WarehouseDTO()}
     *   <li>{@link WarehouseDTO#setItemList(List)}
     *   <li>{@link WarehouseDTO#toString()}
     *   <li>{@link WarehouseDTO#getItemList()}
     * </ul>
     */
    @Test
    void testConstructor() {
        // Arrange and Act
        WarehouseDTO actualWarehouseDTO = new WarehouseDTO();
        ArrayList<Item> itemList = new ArrayList<>();
        actualWarehouseDTO.setItemList(itemList);
        String actualToStringResult = actualWarehouseDTO.toString();

        // Assert that nothing has changed
        assertSame(itemList, actualWarehouseDTO.getItemList());
        assertEquals("WarehouseDTO(name=null, itemList=[])", actualToStringResult);
    }

    /**
     * Method under test: {@link WarehouseDTO#WarehouseDTO(String, List)}
     */
    @Test
    void testConstructor2() {
        // Arrange
        String name = "Name";
        ArrayList<Item> itemList = new ArrayList<>();

        // Act
        WarehouseDTO actualWarehouseDTO = new WarehouseDTO(name, itemList);

        // Assert
        assertTrue(actualWarehouseDTO.getItemList().isEmpty());
        assertEquals("Name", actualWarehouseDTO.getName());
    }


    /**
     * Method under test: {@link WarehouseDTO#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        WarehouseDTO warehouseDTO = new WarehouseDTO();
        WarehouseDTO o = null;

        // Act
        boolean actualEqualsResult = warehouseDTO.equals(o);

        // Assert
        assertNotEquals(warehouseDTO, o);
    }

    /**
     * Method under test: {@link WarehouseDTO#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        WarehouseDTO warehouseDTO = new WarehouseDTO();
        String o = "Different type to WarehouseDTO";

        // Act
        boolean actualEqualsResult = warehouseDTO.equals(o);

        // Assert
        assertNotEquals(warehouseDTO, o);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link WarehouseDTO#equals(Object)}
     *   <li>{@link WarehouseDTO#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3() {
        // Arrange
        WarehouseDTO warehouseDTO = new WarehouseDTO();

        // Act
        boolean actualEqualsResult = warehouseDTO.equals(warehouseDTO);

        // Assert
        assertEquals(warehouseDTO, warehouseDTO);
        int expectedHashCodeResult = warehouseDTO.hashCode();
        assertEquals(expectedHashCodeResult, warehouseDTO.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link WarehouseDTO#equals(Object)}
     *   <li>{@link WarehouseDTO#hashCode()}
     * </ul>
     */
    @Test
    void testEquals4() {
        // Arrange
        WarehouseDTO warehouseDTO = new WarehouseDTO();
        WarehouseDTO warehouseDTO1 = new WarehouseDTO();

        // Act
        boolean actualEqualsResult = warehouseDTO.equals(warehouseDTO1);

        // Assert
        assertEquals(warehouseDTO, warehouseDTO1);
        int expectedHashCodeResult = warehouseDTO.hashCode();
        assertEquals(expectedHashCodeResult, warehouseDTO1.hashCode());
    }
}

