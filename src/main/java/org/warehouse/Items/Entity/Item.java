package org.warehouse.Items.Entity;

import lombok.*;
import org.warehouse.AccountingDocs.Entity.Movement;
import org.warehouse.AccountingDocs.Entity.Receipt;
import org.warehouse.AccountingDocs.Entity.Selling;
import org.warehouse.AccountingDocs.Entity.WriteOff;
import org.warehouse.Warehouses.Entity.Warehouse;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity class for working with items
 */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String sku;

    @Column(nullable = false)
    private String description;

    private Double lastBuyPrice;

    private Double lastSellPrice;

    @Column(nullable = false)
    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "warehouse_id")
    @ToString.Exclude
    private Warehouse warehouse;

    @ManyToMany
    @JoinTable(name = "received_items",
            joinColumns = {
                    @JoinColumn(name = "item_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "receipt_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    @ToString.Exclude
    private Set<Receipt> receiptSet = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "sold_items",
            joinColumns = {
                    @JoinColumn(name = "item_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "selling_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    @ToString.Exclude
    private Set<Selling> sellingSet = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "write_off_items",
            joinColumns = {
                    @JoinColumn(name = "item_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "write_off_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    @ToString.Exclude
    private Set<WriteOff> writeOffSet = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "movement_items",
            joinColumns = {
                    @JoinColumn(name = "item_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "movement_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    @ToString.Exclude
    private Set<Movement> movementSet = new HashSet<>();

}
