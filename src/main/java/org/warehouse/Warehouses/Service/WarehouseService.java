package warehouse.Warehouses.Service;

import org.springframework.stereotype.Service;
import org.warehouse.Items.Entity.Item;
import org.warehouse.Warehouses.Entity.Warehouse;

import java.util.List;

/**
 * Interface for working with warehouses
 */
@Service
public interface WarehouseService {

    public Warehouse checkWarehouseNameAndGetIt(String warehouseName);

    List<Item> getListOfItemsInWarehouse(String warehouseName);
}
