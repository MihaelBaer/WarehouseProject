package org.warehouse.Items.Repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.warehouse.Items.Entity.Item;
import org.warehouse.Warehouses.Entity.Warehouse;

import java.util.List;
import java.util.Optional;

/**
 * Interface for working with items in a database
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findItemBySkuAndWarehouseId(String sku, Long warehouseId);

    @NonNull List<Item> findAll();

    List<Item> findAllBySku(String sku);

    List<Item> findAllByWarehouse(Warehouse warehouse);
}
