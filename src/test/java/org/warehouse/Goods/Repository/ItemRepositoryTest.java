package org.warehouse.Goods.Repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.warehouse.Items.Entity.Item;
import org.warehouse.Items.Repository.ItemRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemRepositoryTest {

    @Mock
    private ItemRepository itemRepository;

    @Test
    @DisplayName("Should return empty list when the warehouse is null")
    void findAllByWarehouseWhenWarehouseIsNullThenReturnEmptyList() {
        Item item = new Item();
        item.setWarehouse(null);

        List<Item> result = itemRepository.findAllByWarehouse(null);

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should return all items when the warehouse is not null")
    void findAllByWarehouseWhenWarehouseIsNotNullThenReturnAllItems() {
        Item item = new Item();
        when(itemRepository.findAllByWarehouse(any())).thenReturn(List.of(item));

        List<Item> result = itemRepository.findAllByWarehouse(any());

        assertEquals(1, result.size());
        assertEquals(item, result.get(0));
    }

    @Test
    @DisplayName("Should return an empty list when there are no items with the given sku")
    void findAllBySkuShouldReturnAnEmptyListWhenThereAreNoItemsWithTheGivenSku() {
        String sku = "sku";
        when(itemRepository.findAllBySku(sku)).thenReturn(List.of());

        List<Item> items = itemRepository.findAllBySku(sku);

        assertTrue(items.isEmpty());
    }

    @Test
    @DisplayName("Should return all items with the given sku")
    void findAllBySkuShouldReturnAllItemsWithTheGivenSku() {
        Item item1 = new Item();
        Item item2 = new Item();
        Item item3 = new Item();
        Item item4 = new Item();

        item1.setSku("sku1");
        item2.setSku("sku2");
        item3.setSku("sku3");
        item4.setSku("sku1");

        when(itemRepository.findAllBySku("sku1")).thenReturn(List.of(item1, item4));

        List<Item> items = itemRepository.findAllBySku("sku1");

        assertEquals(2, items.size());
    }

    @Test
    @DisplayName("Should return all items")
    void findAllShouldReturnAllItems() {
        Item item = new Item();
        when(itemRepository.findAll()).thenReturn(List.of(item));

        List<Item> result = itemRepository.findAll();

        assertEquals(1, result.size());
        assertEquals(item, result.get(0));
    }

    @Test
    @DisplayName("Should return an item when the sku and warehouse id are valid")
    void findItemBySkuAndWarehouseIdWhenSkuAndWarehouseIdAreValidThenReturnAnItem() {
        String sku = "sku";
        Long warehouseId = 1L;
        Item item = new Item();
        when(itemRepository.findItemBySkuAndWarehouseId(sku, warehouseId))
                .thenReturn(Optional.of(item));

        Optional<Item> result = itemRepository.findItemBySkuAndWarehouseId(sku, warehouseId);

        assertTrue(result.isPresent());
        assertEquals(item, result.get());
    }

    @Test
    @DisplayName("Should return an empty optional when the sku and warehouse id are invalid")
    void findItemBySkuAndWarehouseIdWhenSkuAndWarehouseIdAreInvalidThenReturnAnEmptyOptional() {
        String sku = "sku";
        Long warehouseId = 1L;

        when(itemRepository.findItemBySkuAndWarehouseId(sku, warehouseId))
                .thenReturn(Optional.empty());

        assertFalse(itemRepository.findItemBySkuAndWarehouseId(sku, warehouseId).isPresent());
    }
}