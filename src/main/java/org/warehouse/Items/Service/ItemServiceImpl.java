package warehouse.Items.Service;
import org.springframework.stereotype.Service;
import org.warehouse.Items.DTO.ItemDTO;
import org.warehouse.Items.Entity.Item;
import org.warehouse.Items.Repository.ItemRepository;
import org.warehouse.Warehouses.Entity.Warehouse;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class form working with items
 */
@Service
public class ItemServiceImpl implements ItemService {

    ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    /**
     * Method, which adds items from income Receipt to specified warehouse.
     * If item already exists in warehouse, method UPDATES information (amount and buy price) and saves in db.
     * If not - method CREATES new item in warehouse with data from Receipt.
     *
     * @param itemListInDTO - list of items from income Receipt
     * @param warehouse - specified Warehouse instance
     */
    @Override
    public void addReceivedItemsToWarehouse(List<ItemDTO> itemListInDTO, Warehouse warehouse) {
        Item newItem;

        for (ItemDTO itemInDTO : itemListInDTO) {
            Item foundItemInRepo = itemRepository
                    .findItemBySkuAndWarehouseId(itemInDTO.getSku(), warehouse.getId())
                    .orElse(newItem = new Item());

            if (foundItemInRepo.getId() != null) {
                foundItemInRepo.setDescription(itemInDTO.getDescription());
                foundItemInRepo.setAmount(foundItemInRepo.getAmount() + itemInDTO.getAmount());
                foundItemInRepo.setLastBuyPrice(itemInDTO.getLastBuyPrice());
                foundItemInRepo.setWarehouse(warehouse);

                itemRepository.save(foundItemInRepo);
            }
            else  {
                newItem.setSku(itemInDTO.getSku());
                newItem.setDescription(itemInDTO.getDescription());
                newItem.setAmount(itemInDTO.getAmount());
                newItem.setLastBuyPrice(itemInDTO.getLastBuyPrice());
                newItem.setLastSellPrice(itemInDTO.getLastSellPrice());
                newItem.setWarehouse(warehouse);

                itemRepository.save(newItem);
            }
        }
    }

    /**
     * Method, which removes items from income Selling to specified warehouse.
     * If item already exists in warehouse, method only UPDATES information (amount and sell price) and saves in db.
     * If not - method throws Runtime exception.
     * Method also method throws Runtime exception if amount in Selling doc is greater than amount of item in a warehouse
     *
     * @param itemDTOList - list of items from income Selling
     * @param warehouse - specified Warehouse instance
     */
    @Override
    public void removeSoldItemsFromWarehouse(List<ItemDTO> itemDTOList, Warehouse warehouse) {

        for (ItemDTO itemInDTO : itemDTOList) {
            Item foundItemInRepo = itemRepository
                    .findItemBySkuAndWarehouseId(itemInDTO.getSku(), warehouse.getId())
                    .orElseThrow(()-> new RuntimeException("Such item is not found in the warehouse: " +
                            itemInDTO.getDescription() + ". SKU: " + itemInDTO.getSku()));

            checkSufficiencyOfItem(itemInDTO, foundItemInRepo);

            if (foundItemInRepo.getId() != null) {
                foundItemInRepo.setAmount(foundItemInRepo.getAmount() - itemInDTO.getAmount());
                foundItemInRepo.setLastSellPrice(itemInDTO.getLastSellPrice());
                foundItemInRepo.setLastSellPrice(itemInDTO.getLastSellPrice());
                foundItemInRepo.setWarehouse(warehouse);

                itemRepository.save(foundItemInRepo);
            }
        }
    }

    /**
     * Method, which removes items from income WriteOff to specified warehouse.
     * If item already exists in warehouse, method only UPDATES information (only amount, not a sell price) and saves in db.
     * If not - method throws Runtime exception.
     * Method also method throws Runtime exception if amount in WriteOff doc is greater than amount of item in a warehouse.
     *
     * @param itemListInDTO - list of items from income WriteOff
     * @param warehouse - specified Warehouse instance
     */
    @Override
    public void writeOffItemsFromWarehouse(List<ItemDTO> itemListInDTO, Warehouse warehouse) {

        for (ItemDTO itemInDTO : itemListInDTO) {
            Item foundItemInRepo = itemRepository
                    .findItemBySkuAndWarehouseId(itemInDTO.getSku(), warehouse.getId())
                    .orElseThrow(()-> new RuntimeException("Such item is not found in the warehouse: " +
                            itemInDTO.getDescription() + ". SKU: " + itemInDTO.getSku()));

            if (foundItemInRepo.getId() != null) {
                checkSufficiencyOfItem(itemInDTO, foundItemInRepo);
                foundItemInRepo.setAmount(foundItemInRepo.getAmount() - itemInDTO.getAmount());
                itemRepository.save(foundItemInRepo);
            }
        }
    }

    /**
     * Method, which moves items from WriteOff doc between specified warehouses.
     * If item already exists in warehouse< to which items are moved, method only UPDATES information (amount) and saves in db.
     * If not - method creates new Item in this warehouse with data from Movement.
     * Method also method throws Runtime exception if amount of item in Movement doc is greater
     * than amount of item in a warehouse< from which items are moved.
     *
     * @param itemListInDTO - list of items from income WriteOff
     * @param warehouseFrom - specified Warehouse instance, from which items are moved
     * @param warehouseTo - specified Warehouse instance, to which items are moved
     */
    @Override
    public void moveItemsBetweenWarehouses(List<ItemDTO> itemListInDTO,
                                           Warehouse warehouseFrom, Warehouse warehouseTo) {
        Item newItem;

        for (ItemDTO itemInDTO : itemListInDTO) {
            Item foundItemInWarehouseFrom = itemRepository
                    .findItemBySkuAndWarehouseId(itemInDTO.getSku(), warehouseFrom.getId())
                    .orElseThrow(()-> new RuntimeException("Such item is not found in the warehouse, " +
                            "from which is need to move item: " +
                            itemInDTO.getDescription() + ". SKU: " + itemInDTO.getSku()));

            checkSufficiencyOfItem(itemInDTO, foundItemInWarehouseFrom);

            Item foundItemInWarehouseTo = itemRepository
                    .findItemBySkuAndWarehouseId(itemInDTO.getSku(), warehouseTo.getId())
                    .orElse(newItem = new Item());

            if (foundItemInWarehouseTo.getId() != null) {
                foundItemInWarehouseTo.setAmount(foundItemInWarehouseTo.getAmount() + itemInDTO.getAmount());
                foundItemInWarehouseFrom.setAmount(foundItemInWarehouseFrom.getAmount() - itemInDTO.getAmount());

                itemRepository.save(foundItemInWarehouseFrom);
                itemRepository.save(foundItemInWarehouseTo);
            }
            else {
                newItem.setSku(itemInDTO.getSku());
                newItem.setDescription(foundItemInWarehouseFrom.getDescription());
                newItem.setAmount(itemInDTO.getAmount());
                foundItemInWarehouseFrom.setAmount(foundItemInWarehouseFrom.getAmount() - itemInDTO.getAmount());
                newItem.setLastBuyPrice(foundItemInWarehouseFrom.getLastBuyPrice());
                newItem.setLastSellPrice(foundItemInWarehouseFrom.getLastSellPrice());
                newItem.setWarehouse(warehouseTo);

                itemRepository.save(newItem);
            }
        }
    }

    /**
     * Method, which sorts items by SKU
     *
     * @return sorted item list
     */
    public List<Item> getSortedAllItemsFromWarehouses() {
        return itemRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Item::getSku))
                .collect(Collectors.toList());
    }

    /**
     * Helping method, which checks sufficiency of amount in a warehouse.
     * It is used in selling, write-off amd movement processes.
     *
     * @param itemDTO - ItemDTO instance
     * @param itemInRepo - found Item instance in a database
     */
    private void checkSufficiencyOfItem(ItemDTO itemDTO, Item itemInRepo) {
        if (itemDTO.getAmount() > itemInRepo.getAmount()) {
            throw new RuntimeException(String.format("Amount of reduced item (%s) not sufficient. " +
                    "Current amount in warehouse: %s. Amount for reducing: %s."
                    ,itemDTO.getDescription(), itemInRepo.getAmount(), itemDTO.getAmount()));
        }
    }
}
