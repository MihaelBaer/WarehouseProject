package org.warehouse.Goods.DTO;

import org.junit.jupiter.api.Test;
import org.warehouse.Items.DTO.ItemDTO;
import org.warehouse.Warehouses.Entity.Warehouse;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class ItemDTOTest {

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ItemDTO#ItemDTO()}
     *   <li>{@link ItemDTO#setAmount(Integer)}
     *   <li>{@link ItemDTO#setDescription(String)}
     *   <li>{@link ItemDTO#setLastBuyPrice(Double)}
     *   <li>{@link ItemDTO#setLastSellPrice(Double)}
     *   <li>{@link ItemDTO#setSku(String)}
     *   <li>{@link ItemDTO#setWarehouse(Warehouse)}
     *   <li>{@link ItemDTO#toString()}
     *   <li>{@link ItemDTO#getAmount()}
     *   <li>{@link ItemDTO#getDescription()}
     *   <li>{@link ItemDTO#getLastBuyPrice()}
     *   <li>{@link ItemDTO#getLastSellPrice()}
     *   <li>{@link ItemDTO#getSku()}
     *   <li>{@link ItemDTO#getWarehouse()}
     * </ul>
     */
    @Test
    void testConstructor() {
        // Arrange and Act
        ItemDTO actualItemDTO = new ItemDTO();
        int amount = 10;
        actualItemDTO.setAmount(amount);
        String description = "The characteristics of someone or something";
        actualItemDTO.setDescription(description);
        double lastBuyPrice = 10.0d;
        actualItemDTO.setLastBuyPrice(lastBuyPrice);
        double lastSellPrice = 10.0d;
        actualItemDTO.setLastSellPrice(lastSellPrice);
        String sku = "Sku";
        actualItemDTO.setSku(sku);
        Warehouse warehouse = new Warehouse();
        warehouse.setId(123L);
        warehouse.setItemSet(new HashSet<>());
        warehouse.setMovementSet(new HashSet<>());
        warehouse.setName("Name");
        warehouse.setReceiptSet(new HashSet<>());
        warehouse.setSellingSet(new HashSet<>());
        warehouse.setWriteOffSet(new HashSet<>());
        actualItemDTO.setWarehouse(warehouse);
        String actualToStringResult = actualItemDTO.toString();

        // Assert that nothing has changed
        assertEquals(10, actualItemDTO.getAmount().intValue());
        assertEquals("The characteristics of someone or something", actualItemDTO.getDescription());
        assertEquals(10.0d, actualItemDTO.getLastBuyPrice().doubleValue());
        assertEquals(10.0d, actualItemDTO.getLastSellPrice().doubleValue());
        assertEquals("Sku", actualItemDTO.getSku());
        assertSame(warehouse, actualItemDTO.getWarehouse());
        assertEquals("ItemDTO(sku=Sku, description=The characteristics of someone or something, lastBuyPrice=10.0,"
                + " lastSellPrice=10.0, amount=10, warehouse=Warehouse(id=123, name=Name))", actualToStringResult);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ItemDTO#ItemDTO(String, String, Double, Double, Integer, Warehouse)}
     *   <li>{@link ItemDTO#setAmount(Integer)}
     *   <li>{@link ItemDTO#setDescription(String)}
     *   <li>{@link ItemDTO#setLastBuyPrice(Double)}
     *   <li>{@link ItemDTO#setLastSellPrice(Double)}
     *   <li>{@link ItemDTO#setSku(String)}
     *   <li>{@link ItemDTO#setWarehouse(Warehouse)}
     *   <li>{@link ItemDTO#toString()}
     *   <li>{@link ItemDTO#getAmount()}
     *   <li>{@link ItemDTO#getDescription()}
     *   <li>{@link ItemDTO#getLastBuyPrice()}
     *   <li>{@link ItemDTO#getLastSellPrice()}
     *   <li>{@link ItemDTO#getSku()}
     *   <li>{@link ItemDTO#getWarehouse()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        // Arrange
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

        // Act
        ItemDTO actualItemDTO = new ItemDTO(sku, description, lastBuyPrice, lastSellPrice, amount, warehouse);
        int amount1 = 10;
        actualItemDTO.setAmount(amount1);
        String description1 = "The characteristics of someone or something";
        actualItemDTO.setDescription(description1);
        double lastBuyPrice1 = 10.0d;
        actualItemDTO.setLastBuyPrice(lastBuyPrice1);
        double lastSellPrice1 = 10.0d;
        actualItemDTO.setLastSellPrice(lastSellPrice1);
        String sku1 = "Sku";
        actualItemDTO.setSku(sku1);
        Warehouse warehouse1 = new Warehouse();
        warehouse1.setId(123L);
        warehouse1.setItemSet(new HashSet<>());
        warehouse1.setMovementSet(new HashSet<>());
        warehouse1.setName("Name");
        warehouse1.setReceiptSet(new HashSet<>());
        warehouse1.setSellingSet(new HashSet<>());
        warehouse1.setWriteOffSet(new HashSet<>());
        actualItemDTO.setWarehouse(warehouse1);
        String actualToStringResult = actualItemDTO.toString();

        // Assert that nothing has changed
        assertEquals(10, actualItemDTO.getAmount().intValue());
        assertEquals("The characteristics of someone or something", actualItemDTO.getDescription());
        assertEquals(10.0d, actualItemDTO.getLastBuyPrice().doubleValue());
        assertEquals(10.0d, actualItemDTO.getLastSellPrice().doubleValue());
        assertEquals("Sku", actualItemDTO.getSku());
        Warehouse warehouse2 = actualItemDTO.getWarehouse();
        assertSame(warehouse1, warehouse2);
        assertEquals(warehouse, warehouse2);
        assertEquals("ItemDTO(sku=Sku, description=The characteristics of someone or something, lastBuyPrice=10.0,"
                + " lastSellPrice=10.0, amount=10, warehouse=Warehouse(id=123, name=Name))", actualToStringResult);
    }

    /**
     * Method under test: {@link ItemDTO#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        ItemDTO itemDTO = new ItemDTO();
        ItemDTO o = null;

        // Act
        boolean actualEqualsResult = itemDTO.equals(o);

        // Assert
        assertNotEquals(itemDTO, o);
    }

    /**
     * Method under test: {@link ItemDTO#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        ItemDTO itemDTO = new ItemDTO();
        String o = "Different type to ItemDTO";

        // Act
        boolean actualEqualsResult = itemDTO.equals(o);

        // Assert
        assertNotEquals(itemDTO, o);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ItemDTO#equals(Object)}
     *   <li>{@link ItemDTO#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3() {
        // Arrange
        ItemDTO itemDTO = new ItemDTO();

        // Act
        boolean actualEqualsResult = itemDTO.equals(itemDTO);

        // Assert
        assertEquals(itemDTO, itemDTO);
        int expectedHashCodeResult = itemDTO.hashCode();
        assertEquals(expectedHashCodeResult, itemDTO.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ItemDTO#equals(Object)}
     *   <li>{@link ItemDTO#hashCode()}
     * </ul>
     */
    @Test
    void testEquals4() {
        // Arrange
        ItemDTO itemDTO = new ItemDTO();
        ItemDTO itemDTO1 = new ItemDTO();

        // Act
        boolean actualEqualsResult = itemDTO.equals(itemDTO1);

        // Assert
        assertEquals(itemDTO, itemDTO1);
        int expectedHashCodeResult = itemDTO.hashCode();
        assertEquals(expectedHashCodeResult, itemDTO1.hashCode());
    }

    /**
     * Method under test: {@link ItemDTO#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        ItemDTO itemDTO = new ItemDTO("Sku", "The characteristics of someone or something", 10.0d, 10.0d, 10,
                new Warehouse());
        ItemDTO o = new ItemDTO();

        // Act
        boolean actualEqualsResult = itemDTO.equals(o);

        // Assert
        assertNotEquals(itemDTO, o);
    }

    /**
     * Method under test: {@link ItemDTO#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        ItemDTO itemDTO = new ItemDTO();
        ItemDTO o = new ItemDTO("Sku", "The characteristics of someone or something", 10.0d, 10.0d, 10, new Warehouse());

        // Act
        boolean actualEqualsResult = itemDTO.equals(o);

        // Assert
        assertNotEquals(itemDTO, o);
    }

    /**
     * Method under test: {@link ItemDTO#equals(Object)}
     */
    @Test
    void testEquals7() {
        // Arrange
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setSku("Sku");
        ItemDTO o = new ItemDTO();

        // Act
        boolean actualEqualsResult = itemDTO.equals(o);

        // Assert
        assertNotEquals(itemDTO, o);
    }
}

