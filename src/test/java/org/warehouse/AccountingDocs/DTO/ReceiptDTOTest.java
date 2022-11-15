package org.warehouse.AccountingDocs.DTO;

import org.junit.jupiter.api.Test;
import org.warehouse.Items.DTO.ItemDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptDTOTest {

    /**
     * Method under test: {@link ReceiptDTO#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange
        ReceiptDTO receiptDTO = new ReceiptDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());
        String other = "Other";

        // Act
        boolean actualCanEqualResult = receiptDTO.canEqual(other);

        // Assert
        assertFalse(actualCanEqualResult);
    }

    /**
     * Method under test: {@link ReceiptDTO#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        ReceiptDTO receiptDTO = new ReceiptDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());
        ReceiptDTO other = new ReceiptDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());

        // Act
        boolean actualCanEqualResult = receiptDTO.canEqual(other);

        // Assert
        assertTrue(actualCanEqualResult);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ReceiptDTO#ReceiptDTO(String, Integer, String, String, List)}
     *   <li>{@link ReceiptDTO#setTypeDoc(String)}
     *   <li>{@link ReceiptDTO#setWarehouseName(String)}
     *   <li>{@link ReceiptDTO#toString()}
     *   <li>{@link ReceiptDTO#getItemList()}
     *   <li>{@link ReceiptDTO#getNumber()}
     *   <li>{@link ReceiptDTO#getReceiptDate()}
     *   <li>{@link ReceiptDTO#getTypeDoc()}
     *   <li>{@link ReceiptDTO#getWarehouseName()}
     * </ul>
     */
    @Test
    void testConstructor() {
        // Arrange
        String typeDoc = "Type Doc";
        int number = 10;
        String receiptDate = "2020-03-01";
        String warehouseName = "Warehouse Name";
        ArrayList<ItemDTO> itemDTOList = new ArrayList<>();

        // Act
        ReceiptDTO actualReceiptDTO = new ReceiptDTO(typeDoc, number, receiptDate, warehouseName, itemDTOList);
        String typeDoc1 = "Type Doc";
        actualReceiptDTO.setTypeDoc(typeDoc1);
        String warehouseName1 = "Warehouse Name";
        actualReceiptDTO.setWarehouseName(warehouseName1);
        String actualToStringResult = actualReceiptDTO.toString();

        // Assert
        assertSame(itemDTOList, actualReceiptDTO.getItemList());
        assertEquals(10, actualReceiptDTO.getNumber().intValue());
        assertEquals("2020-03-01", actualReceiptDTO.getReceiptDate());
        assertEquals("Type Doc", actualReceiptDTO.getTypeDoc());
        assertEquals("Warehouse Name", actualReceiptDTO.getWarehouseName());
        assertEquals("ReceiptDTO(typeDoc=Type Doc, number=10, receiptDate=2020-03-01, warehouseName=Warehouse Name,"
                + " itemList=[])", actualToStringResult);
    }

    /**
     * Method under test: {@link ReceiptDTO#ReceiptDTO(String, Integer, String, String, List)}
     */
    @Test
    void testConstructor2() {
        // Arrange
        String typeDoc = "Type Doc";
        int number = 10;
        String receiptDate = "2020-03-01";
        String warehouseName = "Warehouse Name";
        ArrayList<ItemDTO> itemList = new ArrayList<>();

        // Act
        ReceiptDTO actualReceiptDTO = new ReceiptDTO(typeDoc, number, receiptDate, warehouseName, itemList);

        // Assert
        assertTrue(actualReceiptDTO.getItemList().isEmpty());
        assertEquals("Warehouse Name", actualReceiptDTO.getWarehouseName());
        assertEquals("Type Doc", actualReceiptDTO.getTypeDoc());
        assertEquals("2020-03-01", actualReceiptDTO.getReceiptDate());
        assertEquals(10, actualReceiptDTO.getNumber().intValue());
    }

    /**
     * Method under test: {@link ReceiptDTO#ReceiptDTO(String, Integer, String, String, List)}
     */
    @Test
    void testConstructor3() {
        // Arrange
        String typeDoc = "foo";
        Integer number = null;
        String receiptDate = null;
        String warehouseName = "foo";
        List<ItemDTO> itemList = null;

        // Assert and act
        assertThrows(NullPointerException.class, () -> new ReceiptDTO(typeDoc, number, receiptDate, warehouseName, itemList));

    }

    /**
     * Method under test: {@link ReceiptDTO#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        ReceiptDTO receiptDTO = new ReceiptDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());
        ReceiptDTO o = null;

        // Act
        boolean actualEqualsResult = receiptDTO.equals(o);

        // Assert
        assertNotEquals(receiptDTO, o);
    }

    /**
     * Method under test: {@link ReceiptDTO#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        ReceiptDTO receiptDTO = new ReceiptDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());
        String o = "Different type to ReceiptDTO";

        // Act
        boolean actualEqualsResult = receiptDTO.equals(o);

        // Assert
        assertNotEquals(receiptDTO, o);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ReceiptDTO#equals(Object)}
     *   <li>{@link ReceiptDTO#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3() {
        // Arrange
        ReceiptDTO receiptDTO = new ReceiptDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());

        // Act
        boolean actualEqualsResult = receiptDTO.equals(receiptDTO);

        // Assert
        assertEquals(receiptDTO, receiptDTO);
        int expectedHashCodeResult = receiptDTO.hashCode();
        assertEquals(expectedHashCodeResult, receiptDTO.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ReceiptDTO#equals(Object)}
     *   <li>{@link ReceiptDTO#hashCode()}
     * </ul>
     */
    @Test
    void testEquals4() {
        // Arrange
        ReceiptDTO receiptDTO = new ReceiptDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());
        ReceiptDTO receiptDTO1 = new ReceiptDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());

        // Act
        boolean actualEqualsResult = receiptDTO.equals(receiptDTO1);

        // Assert
        assertEquals(receiptDTO, receiptDTO1);
        int expectedHashCodeResult = receiptDTO.hashCode();
        assertEquals(expectedHashCodeResult, receiptDTO1.hashCode());
    }

    /**
     * Method under test: {@link ReceiptDTO#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        ReceiptDTO receiptDTO = new ReceiptDTO("2020-03-01", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());
        ReceiptDTO o = new ReceiptDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());

        // Act
        boolean actualEqualsResult = receiptDTO.equals(o);

        // Assert
        assertNotEquals(receiptDTO, o);
    }

    /**
     * Method under test: {@link ReceiptDTO#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        ReceiptDTO receiptDTO = new ReceiptDTO("null", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());
        ReceiptDTO o = new ReceiptDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());

        // Act
        boolean actualEqualsResult = receiptDTO.equals(o);

        // Assert
        assertNotEquals(receiptDTO, o);
    }

    /**
     * Method under test: {@link ReceiptDTO#equals(Object)}
     */
    @Test
    void testEquals7() {
        // Arrange
        ReceiptDTO receiptDTO = new ReceiptDTO("Type Doc", 1, "2020-03-01",
                "Warehouse Name", new ArrayList<>());
        ReceiptDTO o = new ReceiptDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());

        // Act
        boolean actualEqualsResult = receiptDTO.equals(o);

        // Assert
        assertNotEquals(receiptDTO, o);
    }

    /**
     * Method under test: {@link ReceiptDTO#equals(Object)}
     */
    @Test
    void testEquals8() {
        // Arrange
        ReceiptDTO receiptDTO = new ReceiptDTO("Type Doc", 10, "2020/03/01",
                "Warehouse Name", new ArrayList<>());
        ReceiptDTO o = new ReceiptDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());

        // Act
        boolean actualEqualsResult = receiptDTO.equals(o);

        // Assert
        assertNotEquals(receiptDTO, o);
    }

    /**
     * Method under test: {@link ReceiptDTO#setItemList(List)}
     */
    @Test
    void testSetItemList() {
        // Arrange
        ReceiptDTO receiptDTO = new ReceiptDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());
        ArrayList<ItemDTO> itemDTOList = new ArrayList<>();

        // Act
        receiptDTO.setItemList(itemDTOList);

        // Assert
        assertSame(itemDTOList, receiptDTO.getItemList());
    }

    /**
     * Method under test: {@link ReceiptDTO#setItemList(List)}
     */
    @Test
    void testSetItemList2() {
        // Arrange
        ReceiptDTO receiptDTO = new ReceiptDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());
        List<ItemDTO> itemList = null;

        // Assert and act
        assertThrows(NullPointerException.class, () -> receiptDTO.setItemList(itemList));

    }

    /**
     * Method under test: {@link ReceiptDTO#setNumber(Integer)}
     */
    @Test
    void testSetNumber() {
        // Arrange
        ReceiptDTO receiptDTO = new ReceiptDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());
        int number = 10;

        // Act
        receiptDTO.setNumber(number);

        // Assert
        assertEquals(10, receiptDTO.getNumber().intValue());
    }

    /**
     * Method under test: {@link ReceiptDTO#setNumber(Integer)}
     */
    @Test
    void testSetNumber2() {
        // Arrange
        ReceiptDTO receiptDTO = new ReceiptDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());
        Integer number = null;

        // Assert and act
        assertThrows(NullPointerException.class, () -> receiptDTO.setNumber(number));
    }

    /**
     * Method under test: {@link ReceiptDTO#setReceiptDate(String)}
     */
    @Test
    void testSetReceiptDate() {
        // Arrange
        ReceiptDTO receiptDTO = new ReceiptDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());
        String receiptDate = "2020-03-01";

        // Act
        receiptDTO.setReceiptDate(receiptDate);

        // Assert
        assertEquals("2020-03-01", receiptDTO.getReceiptDate());
    }

    /**
     * Method under test: {@link ReceiptDTO#setReceiptDate(String)}
     */
    @Test
    void testSetReceiptDate2() {
        // Arrange
        ReceiptDTO receiptDTO = new ReceiptDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());
        String receiptDate = null;

        // Assert and act
        assertThrows(NullPointerException.class, () -> receiptDTO.setReceiptDate(receiptDate));
    }
}

