package org.warehouse.Warehouses.Service;

import org.springframework.stereotype.Service;
import org.warehouse.Warehouses.Entity.Warehouse;
import org.warehouse.Warehouses.Repository.WarehouseRepository;

import javax.annotation.PostConstruct;

/**
 * Supporting class for construct two warehouses by default along with running an application
 */
@Service
public class DefaultConstruct {

    WarehouseRepository warehouseRepository;

    public DefaultConstruct(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    /**
     * Method creates two warehouses: "Moscow_1" and "Novosibirsk_1"
     */
    @PostConstruct
    private void createDefaultWarehouses() {

        Warehouse moscow = new Warehouse();
        moscow.setName("Moscow_1");

        Warehouse novosibirsk = new Warehouse();
        novosibirsk.setName("Novosibirsk_1");

        warehouseRepository.save(moscow);
        warehouseRepository.save(novosibirsk);
    }
}
