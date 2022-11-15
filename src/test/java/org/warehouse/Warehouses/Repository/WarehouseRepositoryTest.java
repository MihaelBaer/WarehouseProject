package org.warehouse.Warehouses.Repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.warehouse.Warehouses.Entity.Warehouse;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WarehouseRepositoryTest {

    @Mock
    private WarehouseRepository warehouseRepository;

    @Test
    @DisplayName("Should return empty when the name is not found")
    void findWarehouseByNameWhenNameIsNotFound() {
        when(warehouseRepository.findWarehouseByName("name")).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), warehouseRepository.findWarehouseByName("name"));
    }

    @Test
    @DisplayName("Should return the warehouse when the name is found")
    void findWarehouseByNameWhenNameIsFound() {
        Warehouse warehouse = new Warehouse();
        warehouse.setName("Warehouse");
        when(warehouseRepository.findWarehouseByName("Warehouse"))
                .thenReturn(Optional.of(warehouse));

        Optional<Warehouse> result = warehouseRepository.findWarehouseByName("Warehouse");

        assertTrue(result.isPresent());
        assertEquals("Warehouse", result.get().getName());
    }
}