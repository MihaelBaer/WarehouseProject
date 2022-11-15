package org.warehouse.Warehouses.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.warehouse.Items.Entity.Item;
import org.warehouse.Items.Repository.ItemRepository;
import org.warehouse.Warehouses.Entity.Warehouse;
import org.warehouse.Warehouses.Repository.WarehouseRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {WarehouseServiceImpl.class})
@ExtendWith(SpringExtension.class)
class WarehouseServiceImplTest {
    @MockBean
    private ItemRepository itemRepository;

    @MockBean
    private WarehouseRepository warehouseRepository;

    @Autowired
    private WarehouseServiceImpl warehouseServiceImpl;

    /**
     * Method under test: {@link WarehouseServiceImpl#checkWarehouseNameAndGetIt(String)}
     */
    @Test
    void testCheckWarehouseNameAndGetIt() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setMovementSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());
        warehouse.setSellingSet(new HashSet<>());
        warehouse.setWriteOffSet(new HashSet<>());
        Optional<Warehouse> ofResult = Optional.of(warehouse);
        when(warehouseRepository.findWarehouseByName((String) any())).thenReturn(ofResult);
        String warehouseName = "Warehouse Name";

        // Act
        Warehouse actualCheckWarehouseNameAndGetItResult = warehouseServiceImpl.checkWarehouseNameAndGetIt(warehouseName);

        // Assert
        assertSame(warehouse, actualCheckWarehouseNameAndGetItResult);
        verify(warehouseRepository).findWarehouseByName((String) any());
    }

    /**
     * Method under test: {@link WarehouseServiceImpl#checkWarehouseNameAndGetIt(String)}
     */
    @Test
    void testCheckWarehouseNameAndGetIt2() {
        // Arrange
        when(warehouseRepository.findWarehouseByName((String) any())).thenReturn(Optional.empty());
        String warehouseName = "Warehouse Name";

        // Act and Assert
        assertThrows(RuntimeException.class, () -> warehouseServiceImpl.checkWarehouseNameAndGetIt(warehouseName));
        verify(warehouseRepository).findWarehouseByName((String) any());
    }

    /**
     * Method under test: {@link WarehouseServiceImpl#checkWarehouseNameAndGetIt(String)}
     */
    @Test
    void testCheckWarehouseNameAndGetIt3() {
        // Arrange
        when(warehouseRepository.findWarehouseByName((String) any())).thenThrow(new RuntimeException());
        String warehouseName = "Warehouse Name";

        // Act and Assert
        assertThrows(RuntimeException.class, () -> warehouseServiceImpl.checkWarehouseNameAndGetIt(warehouseName));
        verify(warehouseRepository).findWarehouseByName((String) any());
    }

    /**
     * Method under test: {@link WarehouseServiceImpl#getListOfItemsInWarehouse(String)}
     */
    @Test
    void testGetListOfItemsInWarehouse() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setMovementSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());
        warehouse.setSellingSet(new HashSet<>());
        warehouse.setWriteOffSet(new HashSet<>());
        Optional<Warehouse> ofResult = Optional.of(warehouse);
        when(warehouseRepository.findWarehouseByName((String) any())).thenReturn(ofResult);
        when(itemRepository.findAllByWarehouse((Warehouse) any())).thenReturn(new ArrayList<>());
        String warehouseName = "Warehouse Name";

        // Act
        List<Item> actualListOfItemsInWarehouse = warehouseServiceImpl.getListOfItemsInWarehouse(warehouseName);

        // Assert
        assertTrue(actualListOfItemsInWarehouse.isEmpty());
        verify(warehouseRepository).findWarehouseByName((String) any());
        verify(itemRepository).findAllByWarehouse((Warehouse) any());
    }

    /**
     * Method under test: {@link WarehouseServiceImpl#getListOfItemsInWarehouse(String)}
     */
    @Test
    void testGetListOfItemsInWarehouse2() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setMovementSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());
        warehouse.setSellingSet(new HashSet<>());
        warehouse.setWriteOffSet(new HashSet<>());
        Optional<Warehouse> ofResult = Optional.of(warehouse);
        when(warehouseRepository.findWarehouseByName((String) any())).thenReturn(ofResult);
        when(itemRepository.findAllByWarehouse((Warehouse) any())).thenThrow(new RuntimeException());
        String warehouseName = "Warehouse Name";

        // Act and Assert
        assertThrows(RuntimeException.class, () -> warehouseServiceImpl.getListOfItemsInWarehouse(warehouseName));
        verify(warehouseRepository).findWarehouseByName((String) any());
        verify(itemRepository).findAllByWarehouse((Warehouse) any());
    }

    /**
     * Method under test: {@link WarehouseServiceImpl#getListOfItemsInWarehouse(String)}
     */
    @Test
    void testGetListOfItemsInWarehouse3() {
        // Arrange
        when(warehouseRepository.findWarehouseByName((String) any())).thenReturn(Optional.empty());
        when(itemRepository.findAllByWarehouse((Warehouse) any())).thenReturn(new ArrayList<>());
        String warehouseName = "Warehouse Name";

        // Act and Assert
        assertThrows(RuntimeException.class, () -> warehouseServiceImpl.getListOfItemsInWarehouse(warehouseName));
        verify(warehouseRepository).findWarehouseByName((String) any());
    }
}

