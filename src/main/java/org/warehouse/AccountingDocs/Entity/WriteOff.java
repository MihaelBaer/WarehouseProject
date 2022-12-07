package org.warehouse.AccountingDocs.Entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.warehouse.Items.Entity.Item;
import org.warehouse.Warehouses.Entity.Warehouse;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table
public class WriteOff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private Integer number;

    private LocalDate writeOffDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id")
    @ToString.Exclude
    private Warehouse warehouse;

    @ManyToMany(mappedBy = "writeOffSet", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Item> itemSet;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        WriteOff writeOff = (WriteOff) o;
        return id != null && Objects.equals(id, writeOff.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
