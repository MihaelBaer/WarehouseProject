package warehouse.Warehouses.Service;

import org.springframework.stereotype.Service;
import org.warehouse.Items.Entity.Item;
import org.warehouse.Items.Repository.ItemRepository;
import org.warehouse.Warehouses.Entity.Warehouse;
import org.warehouse.Warehouses.Repository.WarehouseRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for working with warehouses
 */
@Service
public class WarehouseServiceImpl implements WarehouseService {

    WarehouseRepository warehouseRepository;
    ItemRepository itemRepository;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository,
                                ItemRepository itemRepository) {
        this.warehouseRepository = warehouseRepository;
        this.itemRepository = itemRepository;
    }

    /**
     * Overriding method, which searches a warehouse in a database by it name.
     * If a warehouse was found, method returns it.
     * If not - method throws RuntimeException
     *
     * @param warehouseName - name of searched warehouse
     * @return - a found warehouse
     */
    @Override
    public Warehouse checkWarehouseNameAndGetIt(String warehouseName) {
        return warehouseRepository.findWarehouseByName(warehouseName).orElseThrow(
                () -> new RuntimeException("Warehouse with such name doesn't exists"));
    }

    /**
     * Overriding method, which searches a set of items in a warehouse, which is stored in a database.
     * Method gets set and sort it by SKU of item, and then returns sorted set.
     *
     * @param warehouseName - name of searched warehouse
     * @return - sorted set of items
     */
    @Override
    public List<Item> getListOfItemsInWarehouse(String warehouseName) {
        Warehouse warehouse = checkWarehouseNameAndGetIt(warehouseName);

        return itemRepository.findAllByWarehouse(warehouse)
                .stream()
                .sorted(Comparator.comparing(Item::getSku))
                .collect(Collectors.toList());
    }
}

