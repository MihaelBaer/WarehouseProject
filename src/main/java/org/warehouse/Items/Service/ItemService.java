package warehouse.Items.Service;

import org.springframework.stereotype.Service;
import org.warehouse.Items.DTO.ItemDTO;
import org.warehouse.Warehouses.Entity.Warehouse;

import java.util.List;

/**
 * Service interface for working with items
 */
@Service
public interface ItemService {

    public void addReceivedItemsToWarehouse(List<ItemDTO> itemListInDTO, Warehouse warehouse);

    public void removeSoldItemsFromWarehouse(List<ItemDTO> itemListInDTO, Warehouse warehouse);

    public void writeOffItemsFromWarehouse(List<ItemDTO> itemListInDTO, Warehouse warehouse);

    public void moveItemsBetweenWarehouses(List<ItemDTO> itemListInDTO, Warehouse warehouseFrom, Warehouse warehouseTo);
}
