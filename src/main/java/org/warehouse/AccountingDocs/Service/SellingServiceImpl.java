package org.warehouse.AccountingDocs.Service;

import org.springframework.stereotype.Service;
import org.warehouse.AccountingDocs.DTO.SellingDTO;
import org.warehouse.AccountingDocs.Entity.Selling;
import org.warehouse.AccountingDocs.Repository.SellingRepository;
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
 * Service class for working with selling docs, which implements SellingService interface
 */
@Service
public class SellingServiceImpl implements SellingService {

    ItemRepository itemRepository;
    ItemService itemService;
    WarehouseService warehouseService;
    SellingRepository sellingRepository;
    DateFormatter dateFormatter;

    public SellingServiceImpl(ItemRepository itemRepository,
                              ItemService itemService,
                              WarehouseService warehouseService,
                              SellingRepository sellingRepository,
                              DateFormatter dateFormatter) {
        this.itemRepository = itemRepository;
        this.itemService = itemService;
        this.warehouseService = warehouseService;
        this.sellingRepository = sellingRepository;
        this.dateFormatter = dateFormatter;
    }

    /**
     * Method checks correct names of warehouse and add new information about items (reduces their amount
     * and updates a sell price) in a found warehouse.
     * After updating, method saves new Selling instance in a db and then binds it with items
     * Method uses helping private methods
     *
     * @param sellingDTO - SellingDTO instance
     */
    @Override
    public void createNewSelling(SellingDTO sellingDTO) {
        Warehouse foundWarehouse = warehouseService.checkWarehouseNameAndGetIt(sellingDTO.getWarehouseName());

        itemService.removeSoldItemsFromWarehouse(sellingDTO.getItemList(), foundWarehouse);

        Selling savedSelling = saveSelling(sellingDTO, foundWarehouse);

        addSellingToEachItem(sellingDTO, savedSelling);
    }

    /**
     * Helping method, which creates and saves a new Selling instance in a database
     *
     * @param sellingDTO - SellingDTO instance
     * @param warehouse - Warehouse instance which found in a database
     * @return saved Selling
     */
    private Selling saveSelling(SellingDTO sellingDTO, Warehouse warehouse) {
        Selling sellingToSave = new Selling();

        sellingToSave.setNumber(sellingDTO.getNumber());
        sellingToSave.setSellingDate(dateFormatter.formatDate(sellingDTO.getSellingDate()));
        sellingToSave.setWarehouse(warehouse);

        sellingRepository.save(sellingToSave);
        return sellingToSave;

    }

    /**
     * Helping method, which add previously saved Selling instance to each item in itemList in selling document
     *
     * @param sellingDTO - SellingDTO instance
     * @param savedSelling - previously saved Selling
     */
    private void addSellingToEachItem(SellingDTO sellingDTO, Selling savedSelling) {

        List<ItemDTO> itemListInSelling = new ArrayList<>(sellingDTO.getItemList());

        for (ItemDTO itemInDTO : itemListInSelling) {
            Item foundItemInRepo = itemRepository
                    .findItemBySkuAndWarehouseId(itemInDTO.getSku(), savedSelling.getWarehouse().getId())
                    .orElse(null);

            assert foundItemInRepo != null;
            foundItemInRepo.getSellingSet().add(savedSelling);
            itemRepository.save(foundItemInRepo);
        }
    }
}
