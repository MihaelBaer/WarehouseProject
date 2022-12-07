package org.warehouse.AccountingDocs.Service;

import org.springframework.stereotype.Service;
import org.warehouse.AccountingDocs.DTO.WriteOffDTO;
import org.warehouse.AccountingDocs.Entity.WriteOff;
import org.warehouse.AccountingDocs.Repository.WriteOffRepository;
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
 * Service class for working with write-off docs, which implements WriteOffService interface
 */
@Service
public class WriteOffServiceImpl implements WriteOffService {

    ItemRepository itemRepository;
    ItemService itemService;
    WarehouseService warehouseService;
    WriteOffRepository writeOffRepository;
    DateFormatter dateFormatter;

    public WriteOffServiceImpl(ItemRepository itemRepository,
                               ItemService itemService,
                               WarehouseService warehouseService,
                               WriteOffRepository writeOffRepository,
                               DateFormatter dateFormatter) {
        this.itemRepository = itemRepository;
        this.itemService = itemService;
        this.warehouseService = warehouseService;
        this.writeOffRepository = writeOffRepository;
        this.dateFormatter = dateFormatter;
    }

    /**
     * Method checks correct names of warehouse and add new information about items (only reduces their amount,
     * not a sell price) in a found warehouse.
     * After updating, method saves new WriteOff instance in a db and then binds it with items
     * Method uses helping private methods
     *
     * @param writeOffDTO - WriteOffDTO instance
     */
    @Override
    public void createNewWriteOff(WriteOffDTO writeOffDTO) {
        Warehouse foundWarehouse = warehouseService.checkWarehouseNameAndGetIt(writeOffDTO.getWarehouseName());

        itemService.writeOffItemsFromWarehouse(writeOffDTO.getItemList(), foundWarehouse);

        WriteOff savedWriteOff = saveWriteOff(writeOffDTO, foundWarehouse);

        addWriteOffToEachItem(writeOffDTO, savedWriteOff);
    }

    /**
     * Helping method, which creates and saves a new WriteOff instance in a database
     *
     * @param writeOffDTO - WriteOffDTO instance
     * @param warehouse - Warehouse instance which found in a database
     * @return saved WriteOff
     */
    private WriteOff saveWriteOff(WriteOffDTO writeOffDTO, Warehouse warehouse) {
        WriteOff writeOffToSave = new WriteOff();

        writeOffToSave.setNumber(writeOffDTO.getNumber());
        writeOffToSave.setWriteOffDate(dateFormatter.formatDate(writeOffDTO.getWriteOffDate()));
        writeOffToSave.setWarehouse(warehouse);

        writeOffRepository.save(writeOffToSave);
        return writeOffToSave;

    }

    /**
     * Helping method, which add previously saved WriteOff instance to each item in itemList in write-off document
     *
     * @param writeOffDTO - WriteOffDTO instance
     * @param savedWriteOff - previously saved WriteOff
     */
    private void addWriteOffToEachItem(WriteOffDTO writeOffDTO, WriteOff savedWriteOff) {

        List<ItemDTO> itemListInWriteOff = new ArrayList<>(writeOffDTO.getItemList());

        for (ItemDTO itemInDTO : itemListInWriteOff) {
            Item foundItemInRepo = itemRepository
                    .findItemBySkuAndWarehouseId(itemInDTO.getSku(), savedWriteOff.getWarehouse().getId())
                    .orElse(null);

            assert foundItemInRepo != null;
            foundItemInRepo.getWriteOffSet().add(savedWriteOff);
            itemRepository.save(foundItemInRepo);
        }
    }
}
