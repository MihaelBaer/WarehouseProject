package org.warehouse.Warehouses.Entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.warehouse.AccountingDocs.Entity.Movement;
import org.warehouse.AccountingDocs.Entity.Receipt;
import org.warehouse.AccountingDocs.Entity.Selling;
import org.warehouse.AccountingDocs.Entity.WriteOff;
import org.warehouse.Items.Entity.Item;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * Entity class for working with warehouses
 */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany
    @JoinColumn(name = "warehouse_id")
    @ToString.Exclude
    private Set<Item> itemSet;

    @OneToMany
    @JoinColumn(name = "warehouse_id")
    @ToString.Exclude
    private Set<Receipt> receiptSet;

    @OneToMany
    @JoinColumn(name = "warehouse_id")
    @ToString.Exclude
    private Set<Selling> sellingSet;

    @OneToMany
    @JoinColumn(name = "warehouse_id")
    @ToString.Exclude
    private Set<Movement> movementSet;

    @OneToMany
    @JoinColumn(name = "warehouse_id")
    @ToString.Exclude
    private Set<WriteOff> writeOffSet;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Warehouse warehouse = (Warehouse) o;
        return id != null && Objects.equals(id, warehouse.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
