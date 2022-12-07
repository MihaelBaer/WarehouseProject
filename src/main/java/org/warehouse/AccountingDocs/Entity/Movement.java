package org.warehouse.AccountingDocs.Entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.warehouse.Items.Entity.Item;
import org.warehouse.Warehouses.Entity.Warehouse;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

/**
 * Entity class for working with movements
 */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private Integer number;

    private LocalDate movementDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_from_id")
    @ToString.Exclude
    private Warehouse warehouseFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_to_id")
    @ToString.Exclude
    private Warehouse warehouseTo;

    @ManyToMany(mappedBy = "movementSet", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Item> itemSet;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Movement movement = (Movement) o;
        return id != null && Objects.equals(id, movement.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
