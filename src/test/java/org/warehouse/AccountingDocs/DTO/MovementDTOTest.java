package org.warehouse.AccountingDocs.DTO;

import org.junit.jupiter.api.Test;
import org.warehouse.Items.DTO.ItemDTO;
import org.warehouse.Warehouses.Entity.Warehouse;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovementDTOTest {

    /**
     * Method under test: {@link MovementDTO#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange
        MovementDTO movementDTO = new MovementDTO("Type Doc", 10, "2020-03-01", "Moscow",
                "Kazan", new ArrayList<>());
        String other = "Other";

        // Act
        boolean actualCanEqualResult = movementDTO.canEqual(other);

        // Assert
        assertFalse(actualCanEqualResult);
    }

    /**
     * Method under test: {@link MovementDTO#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        MovementDTO movementDTO = new MovementDTO("Type Doc", 10, "2020-03-01", "Moscow",
                "Kazan", new ArrayList<>());
        MovementDTO other = new MovementDTO("Type Doc", 10, "2020-03-01", "Moscow",
                "Kazan", new ArrayList<>());

        // Act
        boolean actualCanEqualResult = movementDTO.canEqual(other);

        // Assert
        assertTrue(actualCanEqualResult);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link MovementDTO#MovementDTO(String, Integer, String, String, String, List)}
     *   <li>{@link MovementDTO#setTypeDoc(String)}
     *   <li>{@link MovementDTO#setWarehouseFrom(String)}
     *   <li>{@link MovementDTO#setWarehouseTo(String)}
     *   <li>{@link MovementDTO#toString()}
     *   <li>{@link MovementDTO#getItemList()}
     *   <li>{@link MovementDTO#getMovementDate()}
     *   <li>{@link MovementDTO#getNumber()}
     *   <li>{@link MovementDTO#getTypeDoc()}
     *   <li>{@link MovementDTO#getWarehouseFrom()}
     *   <li>{@link MovementDTO#getWarehouseTo()}
     * </ul>
     */
    @Test
    void testConstructor() {
        // Arrange
        String typeDoc = "Type Doc";
        int number = 10;
        String movementDate = "2020-03-01";
        String warehouseFrom = "Moscow";
        String warehouseTo = "Perm";
        ArrayList<ItemDTO> itemDTOList = new ArrayList<>();

        // Act
        MovementDTO actualMovementDTO = new MovementDTO(typeDoc, number, movementDate, warehouseFrom, warehouseTo,
                itemDTOList);
        String typeDoc1 = "Type Doc";
        actualMovementDTO.setTypeDoc(typeDoc1);
        String warehouseFrom1 = "Kazan";
        actualMovementDTO.setWarehouseFrom(warehouseFrom1);
        String warehouseTo1 = "Korolyov";
        actualMovementDTO.setWarehouseTo(warehouseTo1);
        String actualToStringResult = actualMovementDTO.toString();

        // Assert
        assertSame(itemDTOList, actualMovementDTO.getItemList());
        assertEquals("2020-03-01", actualMovementDTO.getMovementDate());
        assertEquals(10, actualMovementDTO.getNumber().intValue());
        assertEquals("Type Doc", actualMovementDTO.getTypeDoc());
        assertEquals("Kazan", actualMovementDTO.getWarehouseFrom());
        assertEquals("Korolyov", actualMovementDTO.getWarehouseTo());
        assertEquals("MovementDTO(typeDoc=Type Doc, number=10, movementDate=2020-03-01, warehouseFrom=Kazan,"
                + " warehouseTo=Korolyov, itemList=[])", actualToStringResult);
    }

    /**
     * Method under test: {@link MovementDTO#MovementDTO(String, Integer, String, String, String, List)}
     */
    @Test
    void testConstructor2() {
        // Arrange
        String typeDoc = "Type Doc";
        int number = 10;
        String movementDate = "2020-03-01";
        String warehouseFrom = "Kazan" +
                "";
        String warehouseTo = "Moscow";
        ArrayList<ItemDTO> itemList = new ArrayList<>();

        // Act
        MovementDTO actualMovementDTO = new MovementDTO(typeDoc, number, movementDate, warehouseFrom, warehouseTo,
                itemList);

        // Assert
        assertTrue(actualMovementDTO.getItemList().isEmpty());
        assertEquals("Moscow", actualMovementDTO.getWarehouseTo());
        assertEquals("Kazan" +
                "", actualMovementDTO.getWarehouseFrom());
        assertEquals("Type Doc", actualMovementDTO.getTypeDoc());
        assertEquals(10, actualMovementDTO.getNumber().intValue());
        assertEquals("2020-03-01", actualMovementDTO.getMovementDate());
    }

    /**
     * Method under test: {@link MovementDTO#MovementDTO(String, Integer, String, String, String, List)}
     */
    @Test
    void testConstructor3() {
        // Arrange
        String typeDoc = "foo";
        Integer number = null;
        String movementDate = null;
        String warehouseFrom = "foo";
        String warehouseTo = "foo";
        List<ItemDTO> itemList = null;

        // Assert and act
        assertThrows(NullPointerException.class, () -> new MovementDTO(typeDoc, number, movementDate, warehouseFrom, warehouseTo, itemList));
    }


    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link MovementDTO#equals(Object)}
     *   <li>{@link MovementDTO#hashCode()}
     * </ul>
     */
    @Test
    void testEquals() {
        // Arrange
        MovementDTO movementDTO = new MovementDTO("Type Doc", 10, "2020-03-01", "Kazan" +
                "",
                "Moscow", new ArrayList<>());

        // Act
        boolean actualEqualsResult = movementDTO.equals(movementDTO);

        // Assert
        assertEquals(movementDTO, movementDTO);
        int expectedHashCodeResult = movementDTO.hashCode();
        assertEquals(expectedHashCodeResult, movementDTO.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link MovementDTO#equals(Object)}
     *   <li>{@link MovementDTO#hashCode()}
     * </ul>
     */
    @Test
    void testEquals4() {
        // Arrange
        MovementDTO movementDTO = new MovementDTO("Type Doc", 10, "2020-03-01", "Kazan" +
                "",
                "Moscow", new ArrayList<>());
        MovementDTO movementDTO1 = new MovementDTO("Type Doc", 10, "2020-03-01", "Kazan" +
                "",
                "Moscow", new ArrayList<>());

        // Act
        boolean actualEqualsResult = movementDTO.equals(movementDTO1);

        // Assert
        assertEquals(movementDTO, movementDTO1);
        int expectedHashCodeResult = movementDTO.hashCode();
        assertEquals(expectedHashCodeResult, movementDTO1.hashCode());
    }

    /**
     * Method under test: {@link MovementDTO#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        MovementDTO movementDTO = new MovementDTO("2020-03-01", 10, "2020-03-01", "Kazan" +
                "",
                "Moscow", new ArrayList<>());
        MovementDTO o = new MovementDTO("Type Doc", 10, "2020-03-01", "Kazan" +
                "", "Moscow",
                new ArrayList<>());

        // Act
        boolean actualEqualsResult = movementDTO.equals(o);

        // Assert
        assertNotEquals(movementDTO, o);
    }

    /**
     * Method under test: {@link MovementDTO#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        MovementDTO movementDTO = new MovementDTO("null", 10, "2020-03-01", "Kazan" +
                "",
                "Moscow", new ArrayList<>());
        MovementDTO o = new MovementDTO("Type Doc", 10, "2020-03-01", "Kazan" +
                "", "Moscow",
                new ArrayList<>());

        // Act
        boolean actualEqualsResult = movementDTO.equals(o);

        // Assert
        assertNotEquals(movementDTO, o);
    }

    /**
     * Method under test: {@link MovementDTO#equals(Object)}
     */
    @Test
    void testEquals7() {
        // Arrange
        MovementDTO movementDTO = new MovementDTO("Type Doc", 1, "2020-03-01", "Kazan" +
                "",
                "Moscow", new ArrayList<>());
        MovementDTO o = new MovementDTO("Type Doc", 10, "2020-03-01", "Kazan" +
                "", "Moscow",
                new ArrayList<>());

        // Act
        boolean actualEqualsResult = movementDTO.equals(o);

        // Assert
        assertNotEquals(movementDTO, o);
    }

    /**
     * Method under test: {@link MovementDTO#equals(Object)}
     */
    @Test
    void testEquals8() {
        // Arrange
        MovementDTO movementDTO = new MovementDTO("Type Doc", 10, "2020/03/01", "Kazan" +
                "",
                "Moscow", new ArrayList<>());
        MovementDTO o = new MovementDTO("Type Doc", 10, "2020-03-01", "Kazan" +
                "", "Moscow",
                new ArrayList<>());

        // Act
        boolean actualEqualsResult = movementDTO.equals(o);

        // Assert
        assertNotEquals(movementDTO, o);
    }

    /**
     * Method under test: {@link MovementDTO#equals(Object)}
     */
    @Test
    void testEquals9() {
        // Arrange
        MovementDTO movementDTO = new MovementDTO("Type Doc", 10, "2020-03-01", "Type Doc", "Moscow",
                new ArrayList<>());
        MovementDTO o = new MovementDTO("Type Doc", 10, "2020-03-01", "Kazan" +
                "", "Moscow",
                new ArrayList<>());

        // Act
        boolean actualEqualsResult = movementDTO.equals(o);

        // Assert
        assertNotEquals(movementDTO, o);
    }

    /**
     * Method under test: {@link MovementDTO#equals(Object)}
     */
    @Test
    void testEquals10() {
        // Arrange
        MovementDTO movementDTO = new MovementDTO("Type Doc", 10, "2020-03-01", null, "Moscow",
                new ArrayList<>());
        MovementDTO o = new MovementDTO("Type Doc", 10, "2020-03-01", "Kazan" +
                "", "Moscow",
                new ArrayList<>());

        // Act
        boolean actualEqualsResult = movementDTO.equals(o);

        // Assert
        assertNotEquals(movementDTO, o);
    }

    /**
     * Method under test: {@link MovementDTO#equals(Object)}
     */
    @Test
    void testEquals11() {
        // Arrange
        MovementDTO movementDTO = new MovementDTO("Type Doc", 10, "2020-03-01", "Kazan" +
                "", "Type Doc",
                new ArrayList<>());
        MovementDTO o = new MovementDTO("Type Doc", 10, "2020-03-01", "Kazan" +
                "", "Moscow",
                new ArrayList<>());

        // Act
        boolean actualEqualsResult = movementDTO.equals(o);

        // Assert
        assertNotEquals(movementDTO, o);
    }

    /**
     * Method under test: {@link MovementDTO#equals(Object)}
     */
    @Test
    void testEquals12() {
        // Arrange
        MovementDTO movementDTO = new MovementDTO("Type Doc", 10, "2020-03-01", "Kazan" +
                "", null,
                new ArrayList<>());
        MovementDTO o = new MovementDTO("Type Doc", 10, "2020-03-01", "Kazan" +
                "", "Moscow",
                new ArrayList<>());

        // Act
        boolean actualEqualsResult = movementDTO.equals(o);

        // Assert
        assertNotEquals(movementDTO, o);
    }

    /**
     * Method under test: {@link MovementDTO#equals(Object)}
     */
    @Test
    void testEquals13() {
        // Arrange
        ArrayList<ItemDTO> itemDTOList = new ArrayList<>();
        itemDTOList.add(
                new ItemDTO("Type Doc", "The characteristics", 10.0d, 10.0d, 10, new Warehouse()));
        MovementDTO movementDTO = new MovementDTO("Type Doc", 10, "2020-03-01", "Kazan" +
                "",
                "Moscow", itemDTOList);
        MovementDTO o = new MovementDTO("Type Doc", 10, "2020-03-01", "Kazan" +
                "", "Moscow",
                new ArrayList<>());

        // Act
        boolean actualEqualsResult = movementDTO.equals(o);

        // Assert
        assertNotEquals(movementDTO, o);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link MovementDTO#equals(Object)}
     *   <li>{@link MovementDTO#hashCode()}
     * </ul>
     */
    @Test
    void testEquals14() {
        // Arrange
        MovementDTO movementDTO = new MovementDTO("null", 10, "2020-03-01", "Kazan" +
                "",
                "Moscow", new ArrayList<>());
        MovementDTO movementDTO1 = new MovementDTO("null", 10, "2020-03-01", "Kazan" +
                "",
                "Moscow", new ArrayList<>());

        // Act
        boolean actualEqualsResult = movementDTO.equals(movementDTO1);

        // Assert
        assertEquals(movementDTO, movementDTO1);
        int expectedHashCodeResult = movementDTO.hashCode();
        assertEquals(expectedHashCodeResult, movementDTO1.hashCode());
    }

    /**
     * Method under test: {@link MovementDTO#setItemList(List)}
     */
    @Test
    void testSetItemList() {
        // Arrange
        MovementDTO movementDTO = new MovementDTO("Type Doc", 10, "2020-03-01", "Kazan" +
                "",
                "Moscow", new ArrayList<>());
        ArrayList<ItemDTO> itemDTOList = new ArrayList<>();

        // Act
        movementDTO.setItemList(itemDTOList);

        // Assert
        assertSame(itemDTOList, movementDTO.getItemList());
    }

    /**
     * Method under test: {@link MovementDTO#setItemList(List)}
     */
    @Test
    void testSetItemList2() {
        // Arrange
        MovementDTO movementDTO = new MovementDTO("Type Doc", 10, "2020-03-01", "Kazan" +
                "",
                "Moscow", new ArrayList<>());
        List<ItemDTO> itemList = null;

        // Assert and act
        assertThrows(NullPointerException.class, () -> movementDTO.setItemList(itemList));
    }

    /**
     * Method under test: {@link MovementDTO#setMovementDate(String)}
     */
    @Test
    void testSetMovementDate() {
        // Arrange
        MovementDTO movementDTO = new MovementDTO("Type Doc", 10, "2020-03-01", "Kazan" +
                "",
                "Moscow", new ArrayList<>());
        String movementDate = "2020-03-01";

        // Act
        movementDTO.setMovementDate(movementDate);

        // Assert
        assertEquals("2020-03-01", movementDTO.getMovementDate());
    }

    /**
     * Method under test: {@link MovementDTO#setMovementDate(String)}
     */
    @Test
    void testSetMovementDate2() {
        // Arrange
        MovementDTO movementDTO = new MovementDTO("Type Doc", 10, "2020-03-01", "Kazan" +
                "",
                "Moscow", new ArrayList<>());
        String movementDate = null;

        // Assert and act
        assertThrows(NullPointerException.class, () -> movementDTO.setMovementDate(movementDate));
    }

    /**
     * Method under test: {@link MovementDTO#setNumber(Integer)}
     */
    @Test
    void testSetNumber() {
        // Arrange
        MovementDTO movementDTO = new MovementDTO("Type Doc", 10, "2020-03-01", "Kazan" +
                "",
                "Moscow", new ArrayList<>());
        int number = 10;

        // Act
        movementDTO.setNumber(number);

        // Assert
        assertEquals(10, movementDTO.getNumber().intValue());
    }

    /**
     * Method under test: {@link MovementDTO#setNumber(Integer)}
     */
    @Test
    void testSetNumber2() {
        // Arrange
        MovementDTO movementDTO = new MovementDTO("Type Doc", 10, "2020-03-01", "Kazan" +
                "",
                "Moscow", new ArrayList<>());
        Integer number = null;

        // Assert and act
        assertThrows(NullPointerException.class, () -> movementDTO.setNumber(number));
    }
}

