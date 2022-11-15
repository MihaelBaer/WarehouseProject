package org.warehouse.Goods.Entity;

import org.junit.jupiter.api.Test;
import org.warehouse.AccountingDocs.Entity.Movement;
import org.warehouse.AccountingDocs.Entity.Receipt;
import org.warehouse.AccountingDocs.Entity.Selling;
import org.warehouse.AccountingDocs.Entity.WriteOff;
import org.warehouse.Items.Entity.Item;
import org.warehouse.Warehouses.Entity.Warehouse;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class ItemTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Item#Item()}
     *   <li>{@link Item#setAmount(Integer)}
     *   <li>{@link Item#setDescription(String)}
     *   <li>{@link Item#setId(Long)}
     *   <li>{@link Item#setLastBuyPrice(Double)}
     *   <li>{@link Item#setLastSellPrice(Double)}
     *   <li>{@link Item#setMovementSet(Set)}
     *   <li>{@link Item#setReceiptSet(Set)}
     *   <li>{@link Item#setSellingSet(Set)}
     *   <li>{@link Item#setSku(String)}
     *   <li>{@link Item#setWarehouse(Warehouse)}
     *   <li>{@link Item#setWriteOffSet(Set)}
     *   <li>{@link Item#toString()}
     *   <li>{@link Item#getAmount()}
     *   <li>{@link Item#getDescription()}
     *   <li>{@link Item#getId()}
     *   <li>{@link Item#getLastBuyPrice()}
     *   <li>{@link Item#getLastSellPrice()}
     *   <li>{@link Item#getMovementSet()}
     *   <li>{@link Item#getReceiptSet()}
     *   <li>{@link Item#getSellingSet()}
     *   <li>{@link Item#getSku()}
     *   <li>{@link Item#getWarehouse()}
     *   <li>{@link Item#getWriteOffSet()}
     * </ul>
     */
    @Test
    void testConstructor() {
        // Arrange and Act
        Item actualItem = new Item();
        int amount = 10;
        actualItem.setAmount(amount);
        String description = "The characteristics of someone or something";
        actualItem.setDescription(description);
        long id = 123L;
        actualItem.setId(id);
        double lastBuyPrice = 10.0d;
        actualItem.setLastBuyPrice(lastBuyPrice);
        double lastSellPrice = 10.0d;
        actualItem.setLastSellPrice(lastSellPrice);
        HashSet<Movement> movementSet = new HashSet<>();
        actualItem.setMovementSet(movementSet);
        HashSet<Receipt> receiptSet = new HashSet<>();
        actualItem.setReceiptSet(receiptSet);
        HashSet<Selling> sellingSet = new HashSet<>();
        actualItem.setSellingSet(sellingSet);
        String sku = "Sku";
        actualItem.setSku(sku);
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setMovementSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());
        warehouse.setSellingSet(new HashSet<>());
        warehouse.setWriteOffSet(new HashSet<>());
        actualItem.setWarehouse(warehouse);
        HashSet<WriteOff> writeOffSet = new HashSet<>();
        actualItem.setWriteOffSet(writeOffSet);
        String actualToStringResult = actualItem.toString();

        // Assert that nothing has changed
        assertEquals(10, actualItem.getAmount().intValue());
        assertEquals("The characteristics of someone or something", actualItem.getDescription());
        assertEquals(123L, actualItem.getId().longValue());
        assertEquals(10.0d, actualItem.getLastBuyPrice().doubleValue());
        assertEquals(10.0d, actualItem.getLastSellPrice().doubleValue());
        assertSame(movementSet, actualItem.getMovementSet());
        assertSame(receiptSet, actualItem.getReceiptSet());
        assertSame(sellingSet, actualItem.getSellingSet());
        assertEquals("Sku", actualItem.getSku());
        assertSame(warehouse, actualItem.getWarehouse());
        assertSame(writeOffSet, actualItem.getWriteOffSet());
        assertEquals("Item(id=123, sku=Sku, description=The characteristics of someone or something, lastBuyPrice=10.0,"
                + " lastSellPrice=10.0, amount=10)", actualToStringResult);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Item#Item(Long, String, String, Double, Double, Integer, Warehouse, Set, Set, Set, Set)}
     *   <li>{@link Item#setAmount(Integer)}
     *   <li>{@link Item#setDescription(String)}
     *   <li>{@link Item#setId(Long)}
     *   <li>{@link Item#setLastBuyPrice(Double)}
     *   <li>{@link Item#setLastSellPrice(Double)}
     *   <li>{@link Item#setMovementSet(Set)}
     *   <li>{@link Item#setReceiptSet(Set)}
     *   <li>{@link Item#setSellingSet(Set)}
     *   <li>{@link Item#setSku(String)}
     *   <li>{@link Item#setWarehouse(Warehouse)}
     *   <li>{@link Item#setWriteOffSet(Set)}
     *   <li>{@link Item#toString()}
     *   <li>{@link Item#getAmount()}
     *   <li>{@link Item#getDescription()}
     *   <li>{@link Item#getId()}
     *   <li>{@link Item#getLastBuyPrice()}
     *   <li>{@link Item#getLastSellPrice()}
     *   <li>{@link Item#getMovementSet()}
     *   <li>{@link Item#getReceiptSet()}
     *   <li>{@link Item#getSellingSet()}
     *   <li>{@link Item#getSku()}
     *   <li>{@link Item#getWarehouse()}
     *   <li>{@link Item#getWriteOffSet()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        // Arrange
        long id = 123L;
        String sku = "Sku";
        String description = "The characteristics of someone or something";
        double lastBuyPrice = 10.0d;
        double lastSellPrice = 10.0d;
        int amount = 10;

        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setMovementSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());
        warehouse.setSellingSet(new HashSet<>());
        warehouse.setWriteOffSet(new HashSet<>());
        HashSet<Receipt> receiptSet = new HashSet<>();
        HashSet<Selling> sellingSet = new HashSet<>();
        HashSet<WriteOff> writeOffSet = new HashSet<>();
        HashSet<Movement> movementSet = new HashSet<>();

        // Act
        Item actualItem = new Item(id, sku, description, lastBuyPrice, lastSellPrice, amount, warehouse, receiptSet,
                sellingSet, writeOffSet, movementSet);
        int amount1 = 10;
        actualItem.setAmount(amount1);
        String description1 = "The characteristics of someone or something";
        actualItem.setDescription(description1);
        long id1 = 123L;
        actualItem.setId(id1);
        double lastBuyPrice1 = 10.0d;
        actualItem.setLastBuyPrice(lastBuyPrice1);
        double lastSellPrice1 = 10.0d;
        actualItem.setLastSellPrice(lastSellPrice1);
        HashSet<Movement> movementSet1 = new HashSet<>();
        actualItem.setMovementSet(movementSet1);
        HashSet<Receipt> receiptSet1 = new HashSet<>();
        actualItem.setReceiptSet(receiptSet1);
        HashSet<Selling> sellingSet1 = new HashSet<>();
        actualItem.setSellingSet(sellingSet1);
        String sku1 = "Sku";
        actualItem.setSku(sku1);
        Warehouse warehouse1 = new Warehouse();
        warehouse1.setId(123L);
        warehouse1.setItemSet(new HashSet<>());
        warehouse1.setMovementSet(new HashSet<>());
        warehouse1.setName("Name");
        warehouse1.setReceiptSet(new HashSet<>());
        warehouse1.setSellingSet(new HashSet<>());
        warehouse1.setWriteOffSet(new HashSet<>());
        actualItem.setWarehouse(warehouse1);
        HashSet<WriteOff> writeOffSet1 = new HashSet<>();
        actualItem.setWriteOffSet(writeOffSet1);
        String actualToStringResult = actualItem.toString();

        // Assert that nothing has changed
        assertEquals(10, actualItem.getAmount().intValue());
        assertEquals("The characteristics of someone or something", actualItem.getDescription());
        assertEquals(123L, actualItem.getId().longValue());
        assertEquals(10.0d, actualItem.getLastBuyPrice().doubleValue());
        assertEquals(10.0d, actualItem.getLastSellPrice().doubleValue());
        assertSame(movementSet1, actualItem.getMovementSet());
        assertSame(receiptSet1, actualItem.getReceiptSet());
        assertSame(sellingSet1, actualItem.getSellingSet());
        assertEquals("Sku", actualItem.getSku());
        Warehouse warehouse2 = actualItem.getWarehouse();
        assertSame(warehouse1, warehouse2);
        assertEquals(warehouse, warehouse2);
        assertSame(writeOffSet1, actualItem.getWriteOffSet());
        assertEquals("Item(id=123, sku=Sku, description=The characteristics of someone or something, lastBuyPrice=10.0,"
                + " lastSellPrice=10.0, amount=10)", actualToStringResult);
    }
}

