package org.warehouse.Warehouses.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.warehouse.Warehouses.Entity.Warehouse;

import java.util.Optional;

/**
 * Interface for working with warehouses in a database
 */
@Service
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    public Optional<Warehouse> findWarehouseByName(String name);
}
