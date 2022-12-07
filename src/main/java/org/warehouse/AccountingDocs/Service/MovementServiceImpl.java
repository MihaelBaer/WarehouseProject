package org.warehouse.AccountingDocs.Service;

import org.springframework.stereotype.Service;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for working with movement docs, which implements MovementService interface
 */
@Service
public class MovementServiceImpl implements MovementService {

    ItemRepository itemRepository;
    ItemService itemService;
    WarehouseService warehouseService;
    MovementRepository movementRepository;
    DateFormatter dateFormatter;

    public MovementServiceImpl(ItemRepository itemRepository,
                               ItemService itemService,
                               WarehouseService warehouseService,
                               MovementRepository movementRepository,
                               DateFormatter dateFormatter) {
        this.itemRepository = itemRepository;
        this.itemService = itemService;
        this.warehouseService = warehouseService;
        this.movementRepository = movementRepository;
        this.dateFormatter = dateFormatter;
    }

    /**
     * Method checks correct names of warehouses (from and to) and then moves items between warehouses.
     * After moving, method saves new Movement instance in a db and then binds it with items
     * Method uses helping private methods
     *
     * @param movementDTO - income Movement DTO object
     */
    @Override
    public void createNewMovement(MovementDTO movementDTO) {
        Warehouse foundWarehouseFrom = warehouseService.checkWarehouseNameAndGetIt(
                movementDTO.getWarehouseFrom());
        Warehouse foundWarehouseTo = warehouseService.checkWarehouseNameAndGetIt(
                movementDTO.getWarehouseTo());

        itemService.moveItemsBetweenWarehouses(movementDTO.getItemList(), foundWarehouseFrom, foundWarehouseTo);

        Movement savedMovement = saveMovement(movementDTO, foundWarehouseFrom, foundWarehouseTo);

        addMovementToEachItem(movementDTO, savedMovement);
    }

    /**
     * Helping method, which creates and saves a new Movement instance
     *
     * @param movementDTO - MovementDTO instance
     * @param warehouseFrom - Warehouse instance
     * @param warehouseTo - Warehouse instance
     * @return saved Movement instance
     */
    private Movement saveMovement(MovementDTO movementDTO, Warehouse warehouseFrom, Warehouse warehouseTo) {
        Movement movementToSave = new Movement();

        movementToSave.setNumber(movementDTO.getNumber());
        movementToSave.setMovementDate(dateFormatter.formatDate(movementDTO.getMovementDate()));
        movementToSave.setWarehouseFrom(warehouseFrom);
        movementToSave.setWarehouseTo(warehouseTo);

        movementRepository.save(movementToSave);

        return movementToSave;
    }

    /**
     * Helping method, which add previously saved Movement instance to each item in itemList in movement document
     *
     * @param movementDTO - MovementDTO
     * @param movement - previously saved Movement
     */
    private void addMovementToEachItem(MovementDTO movementDTO, Movement movement) {

        List<ItemDTO> itemListInDTO = new ArrayList<>(movementDTO.getItemList());

        for (ItemDTO itemInDTO : itemListInDTO) {
            Item foundItemInWarehouseFrom = itemRepository
                    .findItemBySkuAndWarehouseId(itemInDTO.getSku(), movement.getWarehouseFrom().getId())
                    .orElse(null);

            Item foundItemInWarehouseTo = itemRepository
                    .findItemBySkuAndWarehouseId(itemInDTO.getSku(), movement.getWarehouseTo().getId())
                    .orElse(null);

            assert foundItemInWarehouseFrom != null;
            assert foundItemInWarehouseTo != null;

            foundItemInWarehouseFrom.getMovementSet().add(movement);
            foundItemInWarehouseTo.getMovementSet().add(movement);

            itemRepository.save(foundItemInWarehouseFrom);
            itemRepository.save(foundItemInWarehouseTo);
        }
    }
}
