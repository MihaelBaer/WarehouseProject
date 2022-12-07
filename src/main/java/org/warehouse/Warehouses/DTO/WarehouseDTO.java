package org.warehouse.Warehouses.DTO;

import lombok.*;
import org.warehouse.Items.Entity.Item;

import java.util.List;

/**
 * DTO class for working with warehouses
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class WarehouseDTO {

    @NonNull
    String name;

    List<Item> itemList;
}
