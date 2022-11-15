package org.warehouse.Goods.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.warehouse.Items.DTO.ItemDTO;
import org.warehouse.Items.Entity.Item;
import org.warehouse.Items.Repository.ItemRepository;
import org.warehouse.Items.Service.ItemServiceImpl;
import org.warehouse.Warehouses.Entity.Warehouse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ItemServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ItemServiceImplTest {
    @MockBean
    private ItemRepository itemRepository;

    @Autowired
    private ItemServiceImpl itemServiceImpl;

    /**
     * Method under test: {@link ItemServiceImpl#addReceivedItemsToWarehouse(List, Warehouse)}
     */
    @Test
    void testAddReceivedItemsToWarehouse() {
        // Arrange
        ArrayList<ItemDTO> itemDTOList = new ArrayList<>();
        List<Item> itemList = new ArrayList<>();

        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setMovementSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());
        warehouse.setSellingSet(new HashSet<>());
        warehouse.setWriteOffSet(new HashSet<>());

        // Act
        itemServiceImpl.addReceivedItemsToWarehouse(itemDTOList, warehouse);

        // Assert that nothing has changed
        assertEquals(123L, warehouse.getId().longValue());
        assertTrue(warehouse.getWriteOffSet().isEmpty());
        assertTrue(warehouse.getSellingSet().isEmpty());
        assertTrue(warehouse.getReceiptSet().isEmpty());
        assertEquals("Name", warehouse.getName());
        assertTrue(warehouse.getMovementSet().isEmpty());
        assertTrue(warehouse.getItemSet().isEmpty());
        assertEquals(itemList, itemServiceImpl.getSortedAllItemsFromWarehouses());
    }

    /**
     * Method under test: {@link ItemServiceImpl#addReceivedItemsToWarehouse(List, Warehouse)}
     */
    @Test
    void testAddReceivedItemsToWarehouse2() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setMovementSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());
        warehouse.setSellingSet(new HashSet<>());
        warehouse.setWriteOffSet(new HashSet<>());

        Item item = new Item();
        item.setAmount(10);
        item.setDescription("The characteristics of someone or something");
        item.setId(123L);
        item.setLastBuyPrice(10.0d);
        item.setLastSellPrice(10.0d);
        item.setMovementSet(new HashSet<>());
        item.setReceiptSet(new HashSet<>());
        item.setSellingSet(new HashSet<>());
        item.setSku("Sku");
        item.setWarehouse(warehouse);
        item.setWriteOffSet(new HashSet<>());
        when(itemRepository.save(any())).thenReturn(item);
        when(itemRepository.findItemBySkuAndWarehouseId(any(), any())).thenReturn(Optional.empty());

        ArrayList<ItemDTO> itemDTOList = new ArrayList<>();
        itemDTOList.add(new ItemDTO());

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setId(123L);
        warehouse1.setItemSet(new HashSet<>());
        warehouse1.setMovementSet(new HashSet<>());
        warehouse1.setName("Name");
        warehouse1.setReceiptSet(new HashSet<>());
        warehouse1.setSellingSet(new HashSet<>());
        warehouse1.setWriteOffSet(new HashSet<>());

        // Act
        itemServiceImpl.addReceivedItemsToWarehouse(itemDTOList, warehouse1);

        // Assert
        verify(itemRepository).save(any());
        verify(itemRepository).findItemBySkuAndWarehouseId(any(), any());
    }

    /**
     * Method under test: {@link ItemServiceImpl#addReceivedItemsToWarehouse(List, Warehouse)}
     */
    @Test
    void testAddReceivedItemsToWarehouse3() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setMovementSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());
        warehouse.setSellingSet(new HashSet<>());
        warehouse.setWriteOffSet(new HashSet<>());

        Item item = new Item();
        item.setAmount(10);
        item.setDescription("The characteristics of someone or something");
        item.setId(123L);
        item.setLastBuyPrice(10.0d);
        item.setLastSellPrice(10.0d);
        item.setMovementSet(new HashSet<>());
        item.setReceiptSet(new HashSet<>());
        item.setSellingSet(new HashSet<>());
        item.setSku("Sku");
        item.setWarehouse(warehouse);
        item.setWriteOffSet(new HashSet<>());

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setId(123L);
        warehouse1.setItemSet(new HashSet<>());
        warehouse1.setMovementSet(new HashSet<>());
        warehouse1.setName("Name");
        warehouse1.setReceiptSet(new HashSet<>());
        warehouse1.setSellingSet(new HashSet<>());
        warehouse1.setWriteOffSet(new HashSet<>());

        Item item1 = new Item();
        item1.setAmount(10);
        item1.setDescription("The characteristics of someone or something");
        item1.setId(123L);
        item1.setLastBuyPrice(10.0d);
        item1.setLastSellPrice(10.0d);
        item1.setMovementSet(new HashSet<>());
        item1.setReceiptSet(new HashSet<>());
        item1.setSellingSet(new HashSet<>());
        item1.setSku("Sku");
        item1.setWarehouse(warehouse1);
        item1.setWriteOffSet(new HashSet<>());
        Optional<Item> ofResult = Optional.of(item1);
        when(itemRepository.save(any())).thenReturn(item);
        when(itemRepository.findItemBySkuAndWarehouseId(any(), any())).thenReturn(ofResult);

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setAmount(10);

        ArrayList<ItemDTO> itemDTOList = new ArrayList<>();
        itemDTOList.add(itemDTO);

        Warehouse warehouse2 = new Warehouse();
        warehouse2.setId(123L);
        warehouse2.setItemSet(new HashSet<>());
        warehouse2.setMovementSet(new HashSet<>());
        warehouse2.setName("Name");
        warehouse2.setReceiptSet(new HashSet<>());
        warehouse2.setSellingSet(new HashSet<>());
        warehouse2.setWriteOffSet(new HashSet<>());

        // Act
        itemServiceImpl.addReceivedItemsToWarehouse(itemDTOList, warehouse2);

        // Assert
        verify(itemRepository).save(any());
        verify(itemRepository).findItemBySkuAndWarehouseId(any(), any());
    }

    /**
     * Method under test: {@link ItemServiceImpl#addReceivedItemsToWarehouse(List, Warehouse)}
     */
    @Test
    void testAddReceivedItemsToWarehouse4() {
        // Arrange
        when(itemRepository.save(any())).thenThrow(new RuntimeException());
        when(itemRepository.findItemBySkuAndWarehouseId(any(), any())).thenThrow(new RuntimeException());

        ArrayList<ItemDTO> itemDTOList = new ArrayList<>();
        itemDTOList.add(new ItemDTO());

        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setMovementSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());
        warehouse.setSellingSet(new HashSet<>());
        warehouse.setWriteOffSet(new HashSet<>());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> itemServiceImpl.addReceivedItemsToWarehouse(itemDTOList, warehouse));
        verify(itemRepository).findItemBySkuAndWarehouseId(any(), any());
    }

    /**
     * Method under test: {@link ItemServiceImpl#removeSoldItemsFromWarehouse(List, Warehouse)}
     */
    @Test
    void testRemoveSoldItemsFromWarehouse() {
        // Arrange
        List<ItemDTO> itemDTOList = new ArrayList<>();
        List<Item> itemList = new ArrayList<>();

        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setMovementSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());
        warehouse.setSellingSet(new HashSet<>());
        warehouse.setWriteOffSet(new HashSet<>());

        // Act
        itemServiceImpl.removeSoldItemsFromWarehouse(itemDTOList, warehouse);

        // Assert that nothing has changed
        assertEquals(123L, warehouse.getId().longValue());
        assertTrue(warehouse.getWriteOffSet().isEmpty());
        assertTrue(warehouse.getSellingSet().isEmpty());
        assertTrue(warehouse.getReceiptSet().isEmpty());
        assertEquals("Name", warehouse.getName());
        assertTrue(warehouse.getMovementSet().isEmpty());
        assertTrue(warehouse.getItemSet().isEmpty());
        assertEquals(itemList, itemServiceImpl.getSortedAllItemsFromWarehouses());
    }


    /**
     * Method under test: {@link ItemServiceImpl#removeSoldItemsFromWarehouse(List, Warehouse)}
     */
    @Test
    void testRemoveSoldItemsFromWarehouse2() {
        // Arrange
        when(itemRepository.findItemBySkuAndWarehouseId(any(), any())).thenReturn(Optional.empty());

        ArrayList<ItemDTO> itemDTOList = new ArrayList<>();
        itemDTOList.add(new ItemDTO());

        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setMovementSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());
        warehouse.setSellingSet(new HashSet<>());
        warehouse.setWriteOffSet(new HashSet<>());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> itemServiceImpl.removeSoldItemsFromWarehouse(itemDTOList, warehouse));
        verify(itemRepository).findItemBySkuAndWarehouseId(any(), any());
    }

    /**
     * Method under test: {@link ItemServiceImpl#removeSoldItemsFromWarehouse(List, Warehouse)}
     */
    @Test
    void testRemoveSoldItemsFromWarehouse3() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setMovementSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());
        warehouse.setSellingSet(new HashSet<>());
        warehouse.setWriteOffSet(new HashSet<>());

        Item item = new Item();
        item.setAmount(10);
        item.setDescription("The characteristics of someone or something");
        item.setId(123L);
        item.setLastBuyPrice(10.0d);
        item.setLastSellPrice(10.0d);
        item.setMovementSet(new HashSet<>());
        item.setReceiptSet(new HashSet<>());
        item.setSellingSet(new HashSet<>());
        item.setSku("Sku");
        item.setWarehouse(warehouse);
        item.setWriteOffSet(new HashSet<>());
        Optional<Item> ofResult = Optional.of(item);

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setId(123L);
        warehouse1.setItemSet(new HashSet<>());
        warehouse1.setMovementSet(new HashSet<>());
        warehouse1.setName("Name");
        warehouse1.setReceiptSet(new HashSet<>());
        warehouse1.setSellingSet(new HashSet<>());
        warehouse1.setWriteOffSet(new HashSet<>());

        Item item1 = new Item();
        item1.setAmount(10);
        item1.setDescription("The characteristics of someone or something");
        item1.setId(123L);
        item1.setLastBuyPrice(10.0d);
        item1.setLastSellPrice(10.0d);
        item1.setMovementSet(new HashSet<>());
        item1.setReceiptSet(new HashSet<>());
        item1.setSellingSet(new HashSet<>());
        item1.setSku("Sku");
        item1.setWarehouse(warehouse1);
        item1.setWriteOffSet(new HashSet<>());
        when(itemRepository.save(any())).thenReturn(item1);
        when(itemRepository.findItemBySkuAndWarehouseId(any(), any())).thenReturn(ofResult);

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setAmount(10);

        ArrayList<ItemDTO> itemDTOList = new ArrayList<>();
        itemDTOList.add(itemDTO);

        Warehouse warehouse2 = new Warehouse();
        warehouse2.setId(123L);
        warehouse2.setItemSet(new HashSet<>());
        warehouse2.setMovementSet(new HashSet<>());
        warehouse2.setName("Name");
        warehouse2.setReceiptSet(new HashSet<>());
        warehouse2.setSellingSet(new HashSet<>());
        warehouse2.setWriteOffSet(new HashSet<>());

        // Act
        itemServiceImpl.removeSoldItemsFromWarehouse(itemDTOList, warehouse2);

        // Assert
        verify(itemRepository).save(any());
        verify(itemRepository).findItemBySkuAndWarehouseId(any(), any());
    }

    /**
     * Method under test: {@link ItemServiceImpl#writeOffItemsFromWarehouse(List, Warehouse)}
     */
    @Test
    void testWriteOffItemsFromWarehouse() {
        // Arrange
        ArrayList<ItemDTO> itemDTOList = new ArrayList<>();
        List<Item> itemList = new ArrayList<>();

        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setMovementSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());
        warehouse.setSellingSet(new HashSet<>());
        warehouse.setWriteOffSet(new HashSet<>());

        // Act
        itemServiceImpl.writeOffItemsFromWarehouse(itemDTOList, warehouse);

        // Assert that nothing has changed
        assertEquals(123L, warehouse.getId().longValue());
        assertTrue(warehouse.getWriteOffSet().isEmpty());
        assertTrue(warehouse.getSellingSet().isEmpty());
        assertTrue(warehouse.getReceiptSet().isEmpty());
        assertEquals("Name", warehouse.getName());
        assertTrue(warehouse.getMovementSet().isEmpty());
        assertTrue(warehouse.getItemSet().isEmpty());
        assertEquals(itemList, itemServiceImpl.getSortedAllItemsFromWarehouses());
    }

    /**
     * Method under test: {@link ItemServiceImpl#writeOffItemsFromWarehouse(List, Warehouse)}
     */
    @Test
    void testWriteOffItemsFromWarehouse2() {
        // Arrange
        when(itemRepository.findItemBySkuAndWarehouseId(any(), any())).thenReturn(Optional.empty());

        ArrayList<ItemDTO> itemDTOList = new ArrayList<>();
        itemDTOList.add(new ItemDTO());

        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setMovementSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());
        warehouse.setSellingSet(new HashSet<>());
        warehouse.setWriteOffSet(new HashSet<>());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> itemServiceImpl.writeOffItemsFromWarehouse(itemDTOList, warehouse));
        verify(itemRepository).findItemBySkuAndWarehouseId(any(), any());
    }

    /**
     * Method under test: {@link ItemServiceImpl#writeOffItemsFromWarehouse(List, Warehouse)}
     */
    @Test
    void testWriteOffItemsFromWarehouse3() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setMovementSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());
        warehouse.setSellingSet(new HashSet<>());
        warehouse.setWriteOffSet(new HashSet<>());

        Item item = new Item();
        item.setAmount(10);
        item.setDescription("The characteristics of someone or something");
        item.setId(123L);
        item.setLastBuyPrice(10.0d);
        item.setLastSellPrice(10.0d);
        item.setMovementSet(new HashSet<>());
        item.setReceiptSet(new HashSet<>());
        item.setSellingSet(new HashSet<>());
        item.setSku("Sku");
        item.setWarehouse(warehouse);
        item.setWriteOffSet(new HashSet<>());
        Optional<Item> ofResult = Optional.of(item);

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setId(123L);
        warehouse1.setItemSet(new HashSet<>());
        warehouse1.setMovementSet(new HashSet<>());
        warehouse1.setName("Name");
        warehouse1.setReceiptSet(new HashSet<>());
        warehouse1.setSellingSet(new HashSet<>());
        warehouse1.setWriteOffSet(new HashSet<>());

        Item item1 = new Item();
        item1.setAmount(10);
        item1.setDescription("The characteristics of someone or something");
        item1.setId(123L);
        item1.setLastBuyPrice(10.0d);
        item1.setLastSellPrice(10.0d);
        item1.setMovementSet(new HashSet<>());
        item1.setReceiptSet(new HashSet<>());
        item1.setSellingSet(new HashSet<>());
        item1.setSku("Sku");
        item1.setWarehouse(warehouse1);
        item1.setWriteOffSet(new HashSet<>());
        when(itemRepository.save(any())).thenReturn(item1);
        when(itemRepository.findItemBySkuAndWarehouseId(any(), any())).thenReturn(ofResult);

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setAmount(10);

        ArrayList<ItemDTO> itemDTOList = new ArrayList<>();
        itemDTOList.add(itemDTO);

        Warehouse warehouse2 = new Warehouse();
        warehouse2.setId(123L);
        warehouse2.setItemSet(new HashSet<>());
        warehouse2.setMovementSet(new HashSet<>());
        warehouse2.setName("Name");
        warehouse2.setReceiptSet(new HashSet<>());
        warehouse2.setSellingSet(new HashSet<>());
        warehouse2.setWriteOffSet(new HashSet<>());

        // Act
        itemServiceImpl.writeOffItemsFromWarehouse(itemDTOList, warehouse2);

        // Assert
        verify(itemRepository).save(any());
        verify(itemRepository).findItemBySkuAndWarehouseId(any(), any());
    }

    /**
     * Method under test: {@link ItemServiceImpl#moveItemsBetweenWarehouses(List, Warehouse, Warehouse)}
     */
    @Test
    void testMoveItemsBetweenWarehouses() {
        // Arrange
        ArrayList<ItemDTO> itemDTOList = new ArrayList<>();
        List<Item> itemList = new ArrayList<>();

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
        itemServiceImpl.moveItemsBetweenWarehouses(itemDTOList, warehouse, warehouse1);

        // Assert that nothing has changed
        assertEquals(123L, warehouse.getId().longValue());
        assertTrue(warehouse.getWriteOffSet().isEmpty());
        assertTrue(warehouse.getSellingSet().isEmpty());
        assertTrue(warehouse.getReceiptSet().isEmpty());
        assertEquals("Name", warehouse.getName());
        assertTrue(warehouse.getMovementSet().isEmpty());
        assertTrue(warehouse.getItemSet().isEmpty());
        assertEquals(123L, warehouse1.getId().longValue());
        assertTrue(warehouse1.getWriteOffSet().isEmpty());
        assertTrue(warehouse1.getSellingSet().isEmpty());
        assertTrue(warehouse1.getReceiptSet().isEmpty());
        assertEquals("Name", warehouse1.getName());
        assertTrue(warehouse1.getMovementSet().isEmpty());
        assertTrue(warehouse1.getItemSet().isEmpty());
        assertEquals(itemList, itemServiceImpl.getSortedAllItemsFromWarehouses());
    }

    /**
     * Method under test: {@link ItemServiceImpl#moveItemsBetweenWarehouses(List, Warehouse, Warehouse)}
     */
    @Test
    void testMoveItemsBetweenWarehouses2() {
        // Arrange
        when(itemRepository.findItemBySkuAndWarehouseId(any(), any())).thenReturn(Optional.empty());

        ArrayList<ItemDTO> itemDTOList = new ArrayList<>();
        itemDTOList.add(new ItemDTO());

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

        // Act and Assert
        assertThrows(RuntimeException.class,
                () -> itemServiceImpl.moveItemsBetweenWarehouses(itemDTOList, warehouse, warehouse1));
        verify(itemRepository).findItemBySkuAndWarehouseId(any(), any());
    }

    /**
     * Method under test: {@link ItemServiceImpl#getSortedAllItemsFromWarehouses()}
     */
    @Test
    void testGetSortedAllItemsFromWarehouses() {
        // Arrange
        when(itemRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<Item> actualSortedAllItemsFromWarehouses = itemServiceImpl.getSortedAllItemsFromWarehouses();

        // Assert
        assertTrue(actualSortedAllItemsFromWarehouses.isEmpty());
        verify(itemRepository).findAll();
    }

    /**
     * Method under test: {@link ItemServiceImpl#getSortedAllItemsFromWarehouses()}
     */
    @Test
    void testGetSortedAllItemsFromWarehouses2() {
        // Arrange
        when(itemRepository.findAll()).thenThrow(new RuntimeException());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> itemServiceImpl.getSortedAllItemsFromWarehouses());
        verify(itemRepository).findAll();
    }
}

