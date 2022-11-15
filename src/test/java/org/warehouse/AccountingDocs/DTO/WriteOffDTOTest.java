package org.warehouse.AccountingDocs.DTO;

import org.junit.jupiter.api.Test;
import org.warehouse.Items.DTO.ItemDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WriteOffDTOTest {

    /**
     * Method under test: {@link WriteOffDTO#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange
        WriteOffDTO writeOffDTO = new WriteOffDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());
        String other = "Other";

        // Act
        boolean actualCanEqualResult = writeOffDTO.canEqual(other);

        // Assert
        assertFalse(actualCanEqualResult);
    }

    /**
     * Method under test: {@link WriteOffDTO#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        WriteOffDTO writeOffDTO = new WriteOffDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());
        WriteOffDTO other = new WriteOffDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());

        // Act
        boolean actualCanEqualResult = writeOffDTO.canEqual(other);

        // Assert
        assertTrue(actualCanEqualResult);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link WriteOffDTO#WriteOffDTO(String, Integer, String, String, List)}
     *   <li>{@link WriteOffDTO#setTypeDoc(String)}
     *   <li>{@link WriteOffDTO#setWarehouseName(String)}
     *   <li>{@link WriteOffDTO#toString()}
     *   <li>{@link WriteOffDTO#getItemList()}
     *   <li>{@link WriteOffDTO#getNumber()}
     *   <li>{@link WriteOffDTO#getTypeDoc()}
     *   <li>{@link WriteOffDTO#getWarehouseName()}
     *   <li>{@link WriteOffDTO#getWriteOffDate()}
     * </ul>
     */
    @Test
    void testConstructor() {
        // Arrange
        String typeDoc = "Type Doc";
        int number = 10;
        String writeOffDate = "2020-03-01";
        String warehouseName = "Warehouse Name";
        ArrayList<ItemDTO> itemDTOList = new ArrayList<>();

        // Act
        WriteOffDTO actualWriteOffDTO = new WriteOffDTO(typeDoc, number, writeOffDate, warehouseName, itemDTOList);
        String typeDoc1 = "Type Doc";
        actualWriteOffDTO.setTypeDoc(typeDoc1);
        String warehouseName1 = "Warehouse Name";
        actualWriteOffDTO.setWarehouseName(warehouseName1);
        String actualToStringResult = actualWriteOffDTO.toString();

        // Assert
        assertSame(itemDTOList, actualWriteOffDTO.getItemList());
        assertEquals(10, actualWriteOffDTO.getNumber().intValue());
        assertEquals("Type Doc", actualWriteOffDTO.getTypeDoc());
        assertEquals("Warehouse Name", actualWriteOffDTO.getWarehouseName());
        assertEquals("2020-03-01", actualWriteOffDTO.getWriteOffDate());
        assertEquals("WriteOffDTO(typeDoc=Type Doc, number=10, writeOffDate=2020-03-01, warehouseName=Warehouse Name,"
                + " itemList=[])", actualToStringResult);
    }

    /**
     * Method under test: {@link WriteOffDTO#WriteOffDTO(String, Integer, String, String, List)}
     */
    @Test
    void testConstructor2() {
        // Arrange
        String typeDoc = "Type Doc";
        int number = 10;
        String writeOffDate = "2020-03-01";
        String warehouseName = "Warehouse Name";
        ArrayList<ItemDTO> itemList = new ArrayList<>();

        // Act
        WriteOffDTO actualWriteOffDTO = new WriteOffDTO(typeDoc, number, writeOffDate, warehouseName, itemList);

        // Assert
        assertTrue(actualWriteOffDTO.getItemList().isEmpty());
        assertEquals("2020-03-01", actualWriteOffDTO.getWriteOffDate());
        assertEquals("Warehouse Name", actualWriteOffDTO.getWarehouseName());
        assertEquals("Type Doc", actualWriteOffDTO.getTypeDoc());
        assertEquals(10, actualWriteOffDTO.getNumber().intValue());
    }

    /**
     * Method under test: {@link WriteOffDTO#WriteOffDTO(String, Integer, String, String, List)}
     */
    @Test
    void testConstructor3() {
        // Arrange
        String typeDoc = "foo";
        Integer number = null;
        String writeOffDate = null;
        String warehouseName = "foo";
        List<ItemDTO> itemList = null;

        // Assert and act
        assertThrows(NullPointerException.class, () -> new WriteOffDTO(typeDoc, number, writeOffDate, warehouseName, itemList));
    }

    /**
     * Method under test: {@link WriteOffDTO#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        WriteOffDTO writeOffDTO = new WriteOffDTO("Type Doc", 10, "2020-03-01", "Warehouse Name", new ArrayList<>());
        WriteOffDTO o = null;

        // Act
        boolean actualEqualsResult = writeOffDTO.equals(o);

        // Assert
        assertNotEquals(writeOffDTO, o);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link WriteOffDTO#equals(Object)}
     *   <li>{@link WriteOffDTO#hashCode()}
     * </ul>
     */
    @Test
    void testEquals2() {
        // Arrange
        WriteOffDTO writeOffDTO = new WriteOffDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());

        // Act
        boolean actualEqualsResult = writeOffDTO.equals(writeOffDTO);

        // Assert
        assertEquals(writeOffDTO, writeOffDTO);
        int expectedHashCodeResult = writeOffDTO.hashCode();
        assertEquals(expectedHashCodeResult, writeOffDTO.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link WriteOffDTO#equals(Object)}
     *   <li>{@link WriteOffDTO#hashCode()}
     * </ul>
     */
    @Test
    void testEquals4() {
        // Arrange
        WriteOffDTO writeOffDTO = new WriteOffDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());
        WriteOffDTO writeOffDTO1 = new WriteOffDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());

        // Act
        boolean actualEqualsResult = writeOffDTO.equals(writeOffDTO1);

        // Assert
        assertEquals(writeOffDTO, writeOffDTO1);
        int expectedHashCodeResult = writeOffDTO.hashCode();
        assertEquals(expectedHashCodeResult, writeOffDTO1.hashCode());
    }

    /**
     * Method under test: {@link WriteOffDTO#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        WriteOffDTO writeOffDTO = new WriteOffDTO("2020-03-01", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());
        WriteOffDTO o = new WriteOffDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());

        // Act
        boolean actualEqualsResult = writeOffDTO.equals(o);

        // Assert
        assertNotEquals(writeOffDTO, o);
    }

    /**
     * Method under test: {@link WriteOffDTO#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        WriteOffDTO writeOffDTO = new WriteOffDTO("null", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());
        WriteOffDTO o = new WriteOffDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());

        // Act
        boolean actualEqualsResult = writeOffDTO.equals(o);

        // Assert
        assertNotEquals(writeOffDTO, o);
    }

    /**
     * Method under test: {@link WriteOffDTO#equals(Object)}
     */
    @Test
    void testEquals7() {
        // Arrange
        WriteOffDTO writeOffDTO = new WriteOffDTO("Type Doc", 1, "2020-03-01",
                "Warehouse Name", new ArrayList<>());
        WriteOffDTO o = new WriteOffDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());

        // Act
        boolean actualEqualsResult = writeOffDTO.equals(o);

        // Assert
        assertNotEquals(writeOffDTO, o);
    }

    /**
     * Method under test: {@link WriteOffDTO#equals(Object)}
     */
    @Test
    void testEquals8() {
        // Arrange
        WriteOffDTO writeOffDTO = new WriteOffDTO("Type Doc", 10, "2020/03/01",
                "Warehouse Name", new ArrayList<>());
        WriteOffDTO o = new WriteOffDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());

        // Act
        boolean actualEqualsResult = writeOffDTO.equals(o);

        // Assert
        assertNotEquals(writeOffDTO, o);
    }

    /**
     * Method under test: {@link WriteOffDTO#equals(Object)}
     */
    @Test
    void testEquals9() {
        // Arrange
        WriteOffDTO writeOffDTO = new WriteOffDTO("Type Doc", 10, "2020-03-01",
                "Type Doc", new ArrayList<>());
        WriteOffDTO o = new WriteOffDTO("Type Doc", 10, "2020-03-01",
                "Warehouse Name", new ArrayList<>());

        // Act
        boolean actualEqualsResult = writeOffDTO.equals(o);

        // Assert
        assertNotEquals(writeOffDTO, o);
    }

    /**
     * Method under test: {@link WriteOffDTO#setItemList(List)}
     */
    @Test
    void testSetItemList() {
        // Arrange
        WriteOffDTO writeOffDTO = new WriteOffDTO("Type Doc", 10, "2020-03-01", "Warehouse Name", new ArrayList<>());
        ArrayList<ItemDTO> itemDTOList = new ArrayList<>();

        // Act
        writeOffDTO.setItemList(itemDTOList);

        // Assert
        assertSame(itemDTOList, writeOffDTO.getItemList());
    }

    /**
     * Method under test: {@link WriteOffDTO#setItemList(List)}
     */
    @Test
    void testSetItemList2() {
        // Arrange
        WriteOffDTO writeOffDTO = new WriteOffDTO("Type Doc", 10, "2020-03-01", "Warehouse Name", new ArrayList<>());
        List<ItemDTO> itemList = null;

        // Assert and act
        assertThrows(NullPointerException.class, () -> writeOffDTO.setItemList(itemList));
    }

    /**
     * Method under test: {@link WriteOffDTO#setNumber(Integer)}
     */
    @Test
    void testSetNumber() {
        // Arrange
        WriteOffDTO writeOffDTO = new WriteOffDTO("Type Doc", 10, "2020-03-01", "Warehouse Name", new ArrayList<>());
        int number = 10;

        // Act
        writeOffDTO.setNumber(number);

        // Assert
        assertEquals(10, writeOffDTO.getNumber().intValue());
    }

    /**
     * Method under test: {@link WriteOffDTO#setNumber(Integer)}
     */
    @Test
    void testSetNumber2() {
        // Arrange
        WriteOffDTO writeOffDTO = new WriteOffDTO("Type Doc", 10, "2020-03-01", "Warehouse Name", new ArrayList<>());
        Integer number = null;

        // Assert and act
        assertThrows(NullPointerException.class, () -> writeOffDTO.setNumber(number));
    }

    /**
     * Method under test: {@link WriteOffDTO#setWriteOffDate(String)}
     */
    @Test
    void testSetWriteOffDate() {
        // Arrange
        WriteOffDTO writeOffDTO = new WriteOffDTO("Type Doc", 10, "2020-03-01", "Warehouse Name", new ArrayList<>());
        String writeOffDate = "2020-03-01";

        // Act
        writeOffDTO.setWriteOffDate(writeOffDate);

        // Assert
        assertEquals("2020-03-01", writeOffDTO.getWriteOffDate());
    }

    /**
     * Method under test: {@link WriteOffDTO#setWriteOffDate(String)}
     */
    @Test
    void testSetWriteOffDate2() {
        // Arrange
        WriteOffDTO writeOffDTO = new WriteOffDTO("Type Doc", 10, "2020-03-01", "Warehouse Name", new ArrayList<>());
        String writeOffDate = null;

        // Assert and act
        assertThrows(NullPointerException.class, () -> writeOffDTO.setWriteOffDate(writeOffDate));
    }
}

