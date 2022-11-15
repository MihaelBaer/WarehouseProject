package org.warehouse.AccountingDocs.DTO;

import org.junit.jupiter.api.Test;
import org.warehouse.Items.DTO.ItemDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SellingDTOTest {

    /**
     * Method under test: {@link SellingDTO#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange
        SellingDTO sellingDTO = new SellingDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());
        String other = "Other";

        // Act
        boolean actualCanEqualResult = sellingDTO.canEqual(other);

        // Assert
        assertFalse(actualCanEqualResult);
    }

    /**
     * Method under test: {@link SellingDTO#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        SellingDTO sellingDTO = new SellingDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());
        SellingDTO other = new SellingDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());

        // Act
        boolean actualCanEqualResult = sellingDTO.canEqual(other);

        // Assert
        assertTrue(actualCanEqualResult);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link SellingDTO#SellingDTO(String, Integer, String, String, List)}
     *   <li>{@link SellingDTO#setTypeDoc(String)}
     *   <li>{@link SellingDTO#setWarehouseName(String)}
     *   <li>{@link SellingDTO#toString()}
     *   <li>{@link SellingDTO#getItemList()}
     *   <li>{@link SellingDTO#getNumber()}
     *   <li>{@link SellingDTO#getSellingDate()}
     *   <li>{@link SellingDTO#getTypeDoc()}
     *   <li>{@link SellingDTO#getWarehouseName()}
     * </ul>
     */
    @Test
    void testConstructor() {
        // Arrange
        String typeDoc = "Type Doc";
        int number = 10;
        String sellingDate = "2020-03-01";
        String warehouseName = "Warehouse Name";
        ArrayList<ItemDTO> itemDTOList = new ArrayList<>();

        // Act
        SellingDTO actualSellingDTO = new SellingDTO(typeDoc, number, sellingDate, warehouseName, itemDTOList);
        String typeDoc1 = "Type Doc";
        actualSellingDTO.setTypeDoc(typeDoc1);
        String warehouseName1 = "Warehouse Name";
        actualSellingDTO.setWarehouseName(warehouseName1);
        String actualToStringResult = actualSellingDTO.toString();

        // Assert
        assertSame(itemDTOList, actualSellingDTO.getItemList());
        assertEquals(10, actualSellingDTO.getNumber().intValue());
        assertEquals("2020-03-01", actualSellingDTO.getSellingDate());
        assertEquals("Type Doc", actualSellingDTO.getTypeDoc());
        assertEquals("Warehouse Name", actualSellingDTO.getWarehouseName());
        assertEquals("SellingDTO(typeDoc=Type Doc, number=10, sellingDate=2020-03-01, warehouseName=Warehouse Name,"
                + " itemList=[])", actualToStringResult);
    }

    /**
     * Method under test: {@link SellingDTO#SellingDTO(String, Integer, String, String, List)}
     */
    @Test
    void testConstructor2() {
        // Arrange
        String typeDoc = "Type Doc";
        int number = 10;
        String sellingDate = "2020-03-01";
        String warehouseName = "Warehouse Name";
        ArrayList<ItemDTO> itemList = new ArrayList<>();

        // Act
        SellingDTO actualSellingDTO = new SellingDTO(typeDoc, number, sellingDate, warehouseName, itemList);

        // Assert
        assertTrue(actualSellingDTO.getItemList().isEmpty());
        assertEquals("Warehouse Name", actualSellingDTO.getWarehouseName());
        assertEquals("Type Doc", actualSellingDTO.getTypeDoc());
        assertEquals("2020-03-01", actualSellingDTO.getSellingDate());
        assertEquals(10, actualSellingDTO.getNumber().intValue());
    }

    /**
     * Method under test: {@link SellingDTO#SellingDTO(String, Integer, String, String, List)}
     */
    @Test
    void testConstructor3() {

        // Arrange
        String typeDoc = "foo";
        Integer number = null;
        String sellingDate = null;
        String warehouseName = "foo";
        List<ItemDTO> itemList = null;

        // Assert and act
        assertThrows(NullPointerException.class, () -> new SellingDTO(typeDoc, number, sellingDate, warehouseName, itemList));
    }

    /**
     * Method under test: {@link SellingDTO#SellingDTO(String, Integer, String, String, List)}
     */
    @Test
    void testConstructor4() {
        // Arrange
        String typeDoc = "Type Doc";
        int number = 10;
        String sellingDate = null;
        String warehouseName = "Warehouse Name";
        ArrayList<ItemDTO> itemList = new ArrayList<>();

        // Assert and act
        assertThrows(NullPointerException.class, ()-> new SellingDTO(typeDoc, number, sellingDate, warehouseName, itemList));
    }

    /**
     * Method under test: {@link SellingDTO#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        SellingDTO sellingDTO = new SellingDTO("Type Doc", 10, "2020-03-01", "Warehouse Name", new ArrayList<>());
        SellingDTO o = null;

        // Act
        boolean actualEqualsResult = sellingDTO.equals(o);

        // Assert
        assertNotEquals(sellingDTO, o);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link SellingDTO#equals(Object)}
     *   <li>{@link SellingDTO#hashCode()}
     * </ul>
     */
    @Test
    void testEquals2() {
        // Arrange
        SellingDTO sellingDTO = new SellingDTO("Type Doc", 10, "2020-03-01", "Warehouse Name", new ArrayList<>());

        // Act
        boolean actualEqualsResult = sellingDTO.equals(sellingDTO);

        // Assert
        assertEquals(sellingDTO, sellingDTO);
        int expectedHashCodeResult = sellingDTO.hashCode();
        assertEquals(expectedHashCodeResult, sellingDTO.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link SellingDTO#equals(Object)}
     *   <li>{@link SellingDTO#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3() {
        // Arrange
        SellingDTO sellingDTO = new SellingDTO("Type Doc", 10, "2020-03-01", "Warehouse Name", new ArrayList<>());
        SellingDTO sellingDTO1 = new SellingDTO("Type Doc", 10, "2020-03-01", "Warehouse Name", new ArrayList<>());

        // Act
        boolean actualEqualsResult = sellingDTO.equals(sellingDTO1);

        // Assert
        assertEquals(sellingDTO, sellingDTO1);
        int expectedHashCodeResult = sellingDTO.hashCode();
        assertEquals(expectedHashCodeResult, sellingDTO1.hashCode());
    }

    /**
     * Method under test: {@link SellingDTO#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        SellingDTO sellingDTO = new SellingDTO("2020-03-01", 10, "2020-03-01", "Warehouse Name", new ArrayList<>());
        SellingDTO o = new SellingDTO("Type Doc", 10, "2020-03-01", "Warehouse Name", new ArrayList<>());

        // Act
        boolean actualEqualsResult = sellingDTO.equals(o);

        // Assert
        assertNotEquals(sellingDTO, o);
    }

    /**
     * Method under test: {@link SellingDTO#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        SellingDTO sellingDTO = new SellingDTO("null", 10, "2020-03-01", "Warehouse Name", new ArrayList<>());
        SellingDTO o = new SellingDTO("Type Doc", 10, "2020-03-01", "Warehouse Name", new ArrayList<>());

        // Act
        boolean actualEqualsResult = sellingDTO.equals(o);

        // Assert
        assertNotEquals(sellingDTO, o);
    }

    /**
     * Method under test: {@link SellingDTO#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        SellingDTO sellingDTO = new SellingDTO("Type Doc", 1, "2020-03-01", "Warehouse Name", new ArrayList<>());
        SellingDTO o = new SellingDTO("Type Doc", 10, "2020-03-01", "Warehouse Name", new ArrayList<>());

        // Act
        boolean actualEqualsResult = sellingDTO.equals(o);

        // Assert
        assertNotEquals(sellingDTO, o);
    }

    /**
     * Method under test: {@link SellingDTO#equals(Object)}
     */
    @Test
    void testEquals7() {
        // Arrange
        SellingDTO sellingDTO = new SellingDTO("Type Doc", 10, "2020/03/01", "Warehouse Name", new ArrayList<>());
        SellingDTO o = new SellingDTO("Type Doc", 10, "2020-03-01", "Warehouse Name", new ArrayList<>());

        // Act
        boolean actualEqualsResult = sellingDTO.equals(o);

        // Assert
        assertNotEquals(sellingDTO, o);
    }

    /**
     * Method under test: {@link SellingDTO#equals(Object)}
     */
    @Test
    void testEquals8() {
        // Arrange
        SellingDTO sellingDTO = new SellingDTO("Type Doc", 10, "2020-03-01", "Type Doc", new ArrayList<>());
        SellingDTO o = new SellingDTO("Type Doc", 10, "2020-03-01", "Warehouse Name", new ArrayList<>());

        // Act
        boolean actualEqualsResult = sellingDTO.equals(o);

        // Assert
        assertNotEquals(sellingDTO, o);
    }

    /**
     * Method under test: {@link SellingDTO#setItemList(List)}
     */
    @Test
    void testSetItemList() {
        // Arrange
        SellingDTO sellingDTO = new SellingDTO("Type Doc", 10, "2020-03-01", "Warehouse Name", new ArrayList<>());
        ArrayList<ItemDTO> itemDTOList = new ArrayList<>();

        // Act
        sellingDTO.setItemList(itemDTOList);

        // Assert
        assertSame(itemDTOList, sellingDTO.getItemList());
    }

    /**
     * Method under test: {@link SellingDTO#setItemList(List)}
     */
    @Test
    void testSetItemList2() {
        // Arrange
        SellingDTO sellingDTO = new SellingDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());
        List<ItemDTO> itemList = null;

        // Assert and act
        assertThrows(NullPointerException.class, () -> sellingDTO.setItemList(itemList));
    }

    /**
     * Method under test: {@link SellingDTO#setNumber(Integer)}
     */
    @Test
    void testSetNumber() {
        // Arrange
        SellingDTO sellingDTO = new SellingDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());
        int number = 10;

        // Act
        sellingDTO.setNumber(number);

        // Assert
        assertEquals(10, sellingDTO.getNumber().intValue());
    }

    /**
     * Method under test: {@link SellingDTO#setNumber(Integer)}
     */
    @Test
    void testSetNumber2() {
        // Arrange
        SellingDTO sellingDTO = new SellingDTO("Type Doc", 10, "2020-03-01", "Warehouse Name", new ArrayList<>());
        Integer number = null;

        // Assert and act
        assertThrows(NullPointerException.class, () -> sellingDTO.setNumber(number));
    }

    /**
     * Method under test: {@link SellingDTO#setSellingDate(String)}
     */
    @Test
    void testSetSellingDate() {
        // Arrange
        SellingDTO sellingDTO = new SellingDTO("Type Doc", 10, "2020-03-01", "Warehouse Name", new ArrayList<>());
        String sellingDate = "2020-03-01";

        // Act
        sellingDTO.setSellingDate(sellingDate);

        // Assert
        assertEquals("2020-03-01", sellingDTO.getSellingDate());
    }

    /**
     * Method under test: {@link SellingDTO#setSellingDate(String)}
     */
    @Test
    void testSetSellingDate2() {
        // Arrange
        SellingDTO sellingDTO = new SellingDTO("Type Doc", 10, "2020-03-01", "Warehouse Name", new ArrayList<>());
        String sellingDate = null;

        // Act
        assertThrows(NullPointerException.class, () -> sellingDTO.setSellingDate(sellingDate));
    }
}

