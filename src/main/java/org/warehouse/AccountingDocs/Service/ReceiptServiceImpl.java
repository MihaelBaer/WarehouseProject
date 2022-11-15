package warehouse.AccountingDocs.Service;

import org.springframework.stereotype.Service;
import org.warehouse.AccountingDocs.DTO.ReceiptDTO;
import org.warehouse.AccountingDocs.Entity.Receipt;
import org.warehouse.AccountingDocs.Repository.ReceiptRepository;
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
 * Service class for working with receipt docs, which implements ReceiptService interface
 */
@Service
public class ReceiptServiceImpl implements ReceiptService {

    ItemRepository itemRepository;
    ItemService itemService;
    WarehouseService warehouseService;
    ReceiptRepository receiptRepository;
    DateFormatter dateFormatter;

    public ReceiptServiceImpl(ItemRepository itemRepository,
                              ItemService itemService,
                              WarehouseService warehouseService,
                              ReceiptRepository receiptRepository,
                              DateFormatter dateFormatter) {
        this.itemRepository = itemRepository;
        this.itemService = itemService;
        this.warehouseService = warehouseService;
        this.receiptRepository = receiptRepository;
        this.dateFormatter = dateFormatter;
    }

    /**
     * Method checks correct names of warehouse and add items in a found warehouse.
     * After adding, method saves new Receipt instance in a db and then binds it with items
     * Method uses helping private methods
     *
     * @param receiptDTO - ReceiptDTO instance
     */
    @Override
    public void createNewReceipt(ReceiptDTO receiptDTO) {
        Warehouse foundWarehouse = warehouseService.checkWarehouseNameAndGetIt(receiptDTO.getWarehouseName());

        itemService.addReceivedItemsToWarehouse(receiptDTO.getItemList(), foundWarehouse);

        Receipt savedReceipt = saveReceipt(receiptDTO, foundWarehouse);

        addReceiptToEachItem(receiptDTO, savedReceipt);
    }

    /**
     * Helping method, which creates and saves a new Receipt instance in a database
     *
     * @param receiptDTO - ReceiptDTO instance
     * @param warehouse - Warehouse instance which found in a database
     * @return - saved Receipt
     */
    private Receipt saveReceipt(ReceiptDTO receiptDTO, Warehouse warehouse) {
        Receipt receiptToSave = new Receipt();

        receiptToSave.setNumber(receiptDTO.getNumber());
        receiptToSave.setReceiptDate(dateFormatter.formatDate(receiptDTO.getReceiptDate()));
        receiptToSave.setWarehouse(warehouse);

        receiptRepository.save(receiptToSave);

        return receiptToSave;

    }

    /**
     * Helping method, which add previously saved Receipt instance to each item in itemList in receipt document
     *
     * @param receiptDTO - ReceiptDTO instance
     * @param savedReceipt - previously saved Receipt
     */
    private void addReceiptToEachItem(ReceiptDTO receiptDTO, Receipt savedReceipt) {

        List<ItemDTO> itemListInDTO = new ArrayList<>(receiptDTO.getItemList());

        for (ItemDTO itemInDTO : itemListInDTO) {
            Item foundItemInRepo = itemRepository
                    .findItemBySkuAndWarehouseId(itemInDTO.getSku(), savedReceipt.getWarehouse().getId())
                    .orElse(null);

            assert foundItemInRepo != null;
            foundItemInRepo.getReceiptSet().add(savedReceipt);
            itemRepository.save(foundItemInRepo);
        }
    }
}

