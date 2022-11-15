package org.warehouse.AccountingDocs.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.warehouse.AccountingDocs.DTO.MovementDTO;
import org.warehouse.AccountingDocs.Entity.Movement;
import org.warehouse.AccountingDocs.Repository.MovementRepository;
import org.warehouse.Items.DTO.ItemDTO;
import org.warehouse.Items.Entity.Item;
import org.warehouse.Items.Repository.ItemRepository;
import org.warehouse.Items.Service.ItemService;
import org.warehouse.SupportingClasses.DateFormatter;
import org.warehouse.Warehouses.Entity.Warehouse;
import org.warehouse.Warehouses.Service.WarehouseService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {MovementServiceImpl.class})
@ExtendWith(SpringExtension.class)
class MovementServiceImplTest {
    @MockBean
    private DateFormatter dateFormatter;

    @MockBean
    private ItemRepository itemRepository;

    @MockBean
    private ItemService itemService;

    @MockBean
    private MovementRepository movementRepository;

    @Autowired
    private MovementServiceImpl movementServiceImpl;

    @MockBean
    private WarehouseService warehouseService;

    /**
     * Method under test: {@link MovementServiceImpl#createNewMovement(MovementDTO)}
     */
    @Test
    void testCreateNewMovement() {
        // Arrange
        doNothing().when(itemService)
                .moveItemsBetweenWarehouses(any(), (Warehouse) any(), (Warehouse) any());

        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setMovementSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());
        warehouse.setSellingSet(new HashSet<>());
        warehouse.setWriteOffSet(new HashSet<>());
        when(warehouseService.checkWarehouseNameAndGetIt((String) any())).thenReturn(warehouse);

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setId(123L);
        warehouse1.setItemSet(new HashSet<>());
        warehouse1.setMovementSet(new HashSet<>());
        warehouse1.setName("Name");
        warehouse1.setReceiptSet(new HashSet<>());
        warehouse1.setSellingSet(new HashSet<>());
        warehouse1.setWriteOffSet(new HashSet<>());

        Warehouse warehouse2 = new Warehouse();
        warehouse2.setId(123L);
        warehouse2.setItemSet(new HashSet<>());
        warehouse2.setMovementSet(new HashSet<>());
        warehouse2.setName("Name");
        warehouse2.setReceiptSet(new HashSet<>());
        warehouse2.setSellingSet(new HashSet<>());
        warehouse2.setWriteOffSet(new HashSet<>());

        Movement movement = new Movement();
        movement.setId(123L);
        movement.setItemSet(new HashSet<>());
        movement.setMovementDate(LocalDate.ofEpochDay(1L));
        movement.setNumber(10);
        movement.setWarehouseFrom(warehouse1);
        movement.setWarehouseTo(warehouse2);
        when(movementRepository.save((Movement) any())).thenReturn(movement);
        when(dateFormatter.formatDate((String) any())).thenReturn(LocalDate.ofEpochDay(1L));

        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setItemList(new ArrayList<>());

        // Act
        movementServiceImpl.createNewMovement(movementDTO);

        // Assert
        verify(itemService).moveItemsBetweenWarehouses((List<ItemDTO>) any(), (Warehouse) any(), (Warehouse) any());
        verify(warehouseService, atLeast(1)).checkWarehouseNameAndGetIt((String) any());
        verify(movementRepository).save((Movement) any());
        verify(dateFormatter).formatDate((String) any());
    }

    /**
     * Method under test: {@link MovementServiceImpl#createNewMovement(MovementDTO)}
     */
    @Test
    void testCreateNewMovement2() {
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
        when(itemRepository.save((Item) any())).thenReturn(item1);
        when(itemRepository.findItemBySkuAndWarehouseId((String) any(), (Long) any())).thenReturn(ofResult);
        doNothing().when(itemService)
                .moveItemsBetweenWarehouses((List<ItemDTO>) any(), (Warehouse) any(), (Warehouse) any());

        Warehouse warehouse2 = new Warehouse();
        warehouse2.setId(123L);
        warehouse2.setItemSet(new HashSet<>());
        warehouse2.setMovementSet(new HashSet<>());
        warehouse2.setName("Name");
        warehouse2.setReceiptSet(new HashSet<>());
        warehouse2.setSellingSet(new HashSet<>());
        warehouse2.setWriteOffSet(new HashSet<>());
        when(warehouseService.checkWarehouseNameAndGetIt((String) any())).thenReturn(warehouse2);

        Warehouse warehouse3 = new Warehouse();
        warehouse3.setId(123L);
        warehouse3.setItemSet(new HashSet<>());
        warehouse3.setMovementSet(new HashSet<>());
        warehouse3.setName("Name");
        warehouse3.setReceiptSet(new HashSet<>());
        warehouse3.setSellingSet(new HashSet<>());
        warehouse3.setWriteOffSet(new HashSet<>());

        Warehouse warehouse4 = new Warehouse();
        warehouse4.setId(123L);
        warehouse4.setItemSet(new HashSet<>());
        warehouse4.setMovementSet(new HashSet<>());
        warehouse4.setName("Name");
        warehouse4.setReceiptSet(new HashSet<>());
        warehouse4.setSellingSet(new HashSet<>());
        warehouse4.setWriteOffSet(new HashSet<>());

        Movement movement = new Movement();
        movement.setId(123L);
        movement.setItemSet(new HashSet<>());
        movement.setMovementDate(LocalDate.ofEpochDay(1L));
        movement.setNumber(10);
        movement.setWarehouseFrom(warehouse3);
        movement.setWarehouseTo(warehouse4);
        when(movementRepository.save((Movement) any())).thenReturn(movement);
        when(dateFormatter.formatDate((String) any())).thenReturn(LocalDate.ofEpochDay(1L));

        ArrayList<ItemDTO> itemDTOList = new ArrayList<>();
        itemDTOList.add(new ItemDTO());

        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setItemList(itemDTOList);

        // Act
        movementServiceImpl.createNewMovement(movementDTO);

        // Assert
        verify(itemRepository, atLeast(1)).save((Item) any());
        verify(itemRepository, atLeast(1)).findItemBySkuAndWarehouseId((String) any(), (Long) any());
        verify(itemService).moveItemsBetweenWarehouses((List<ItemDTO>) any(), (Warehouse) any(), (Warehouse) any());
        verify(warehouseService, atLeast(1)).checkWarehouseNameAndGetIt((String) any());
        verify(movementRepository).save((Movement) any());
        verify(dateFormatter).formatDate((String) any());
    }
}

