package org.warehouse.SupportClasses;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.warehouse.AccountingDocs.Entity.Movement;
import org.warehouse.AccountingDocs.Entity.Receipt;
import org.warehouse.AccountingDocs.Entity.Selling;
import org.warehouse.AccountingDocs.Entity.WriteOff;
import org.warehouse.Items.Entity.Item;
import org.warehouse.SupportingClasses.JsonFormatterImpl;
import org.warehouse.Warehouses.Entity.Warehouse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class JsonFormatterImplTest {
    @InjectMocks
    private JsonFormatterImpl jsonFormatter;



    @Test
    @DisplayName("Should return a json string with the receipt data")
    void convertForReceiptShouldReturnJsonStringWithReceiptData() {
        // Arrange
        Item item1 = new Item();
        item1.setSku("SKU1");
        item1.setDescription("Description 1");
        item1.setAmount(10);
        item1.setLastBuyPrice(10.0);

        Item item2 = new Item();
        item2.setSku("SKU2");
        item2.setDescription("Description 2");
        item2.setAmount(20);
        item2.setLastBuyPrice(20.0);

        Set<Item> itemSet = new HashSet<>();
        itemSet.add(item1);
        itemSet.add(item2);

        Warehouse warehouse = new Warehouse();
        warehouse.setName("Warehouse 1");

        Receipt receipt = new Receipt();
        receipt.setId(1L);
        receipt.setNumber(1);
        receipt.setReceiptDate(LocalDate.of(2022, 11, 11));
        receipt.setWarehouse(warehouse);
        receipt.setItemSet(itemSet);

        // Act
        String jsonString = jsonFormatter.convertForReceipt(receipt);

        JSONObject jsonObject = new JSONObject(jsonString);

        // Assert
        assertEquals("Receipt", jsonObject.getString("Document type"));
        assertEquals(receipt.getNumber(), jsonObject.getInt("Number"));
        assertEquals(receipt.getReceiptDate().toString(), jsonObject.get("Date").toString());
        assertEquals(receipt.getWarehouse().getName(), jsonObject.getString("Warehouse"));
        assertEquals(receipt.getItemSet(), itemSet);
    }

    @Test
    @DisplayName("Should return a json string with the selling data")
    void convertForReceiptShouldReturnJsonStringWithSellingData() {
        // Arrange
        Item item1 = new Item();
        item1.setSku("SKU1");
        item1.setDescription("Description 1");
        item1.setAmount(10);
        item1.setLastSellPrice(10.0);

        Item item2 = new Item();
        item2.setSku("SKU2");
        item2.setDescription("Description 2");
        item2.setAmount(20);
        item2.setLastSellPrice(20.0);

        Set<Item> itemSet = new HashSet<>();
        itemSet.add(item1);
        itemSet.add(item2);

        Warehouse warehouse = new Warehouse();
        warehouse.setName("Warehouse 1");

        Selling selling = new Selling();
        selling.setId(1L);
        selling.setNumber(1);
        selling.setSellingDate(LocalDate.of(2022, 11, 11));
        selling.setWarehouse(warehouse);
        selling.setItemSet(itemSet);

        // Act
        String jsonString = jsonFormatter.convertForSelling(selling);
        JSONObject jsonObject = new JSONObject(jsonString);

        // Assert
        assertEquals("Selling", jsonObject.getString("Document type"));
        assertEquals(selling.getNumber(), jsonObject.getInt("Number"));
        assertEquals(selling.getSellingDate().toString(), jsonObject.get("Date").toString());
        assertEquals(selling.getWarehouse().getName(), jsonObject.getString("Warehouse"));
        assertEquals(selling.getItemSet(), itemSet);
    }

    @Test
    @DisplayName("Should return a json string with the movement data")
    void convertForReceiptShouldReturnJsonStringWithMovementData() {
        // Arrange
        Item item1 = new Item();
        item1.setSku("SKU1");
        item1.setDescription("Description 1");
        item1.setAmount(10);
        item1.setLastSellPrice(10.0);
        item1.setLastBuyPrice(10.0);

        Item item2 = new Item();
        item2.setSku("SKU2");
        item2.setDescription("Description 2");
        item2.setAmount(20);
        item2.setLastSellPrice(20.0);
        item2.setLastBuyPrice(10.0);

        Set<Item> itemSet = new HashSet<>();
        itemSet.add(item1);
        itemSet.add(item2);

        Warehouse warehouseFrom = new Warehouse();
        warehouseFrom.setName("Warehouse 1");

        Warehouse warehouseTo = new Warehouse();
        warehouseTo.setName("Warehouse 2");

        Movement movement = new Movement();
        movement.setId(1L);
        movement.setNumber(1);
        movement.setMovementDate(LocalDate.of(2022, 11, 11));
        movement.setWarehouseFrom(warehouseFrom);
        movement.setWarehouseTo(warehouseTo);
        movement.setItemSet(itemSet);

        // Act
        String jsonString = jsonFormatter.convertForMovement(movement);
        JSONObject jsonObject = new JSONObject(jsonString);

        // Assert
        assertEquals("Movement", jsonObject.getString("Document type"));
        assertEquals(movement.getNumber(), jsonObject.getInt("Number"));
        assertEquals(movement.getMovementDate().toString(), jsonObject.get("Date").toString());
        assertEquals(movement.getWarehouseFrom().getName(), jsonObject.getString("From"));
        assertEquals(movement.getWarehouseTo().getName(), jsonObject.getString("To"));
        assertEquals(movement.getItemSet(), itemSet);
    }

    @Test
    @DisplayName("Should return a json string with the write-off data")
    void convertForReceiptShouldReturnJsonStringWithWriteOffData() {
        // Arrange
        Item item1 = new Item();
        item1.setSku("SKU1");
        item1.setDescription("Description 1");
        item1.setAmount(10);
        item1.setLastSellPrice(10.0);

        Item item2 = new Item();
        item2.setSku("SKU2");
        item2.setDescription("Description 2");
        item2.setAmount(20);
        item2.setLastSellPrice(20.0);

        Set<Item> itemSet = new HashSet<>();
        itemSet.add(item1);
        itemSet.add(item2);

        Warehouse warehouse = new Warehouse();
        warehouse.setName("Warehouse 1");

        WriteOff writeOff = new WriteOff();
        writeOff.setId(1L);
        writeOff.setNumber(1);
        writeOff.setWriteOffDate(LocalDate.of(2022, 11, 11));
        writeOff.setWarehouse(warehouse);
        writeOff.setItemSet(itemSet);

        // Act
        String jsonString = jsonFormatter.convertForWriteOff(writeOff);
        JSONObject jsonObject = new JSONObject(jsonString);

        // Assert
        assertEquals("Write-off", jsonObject.getString("Document type"));
        assertEquals(writeOff.getNumber(), jsonObject.getInt("Number"));
        assertEquals(writeOff.getWriteOffDate().toString(), jsonObject.get("Date").toString());
        assertEquals(writeOff.getWarehouse().getName(), jsonObject.getString("Warehouse"));
        assertEquals(writeOff.getItemSet(), itemSet);
    }


    @Test
    @DisplayName("Should return a json string with the correct format for warehouse reports")
    void convertForWarehouseReportsShouldReturnJsonStringWithCorrectFormat() {
        List<Item> itemList = new ArrayList<>();
        Item item = new Item();
        item.setSku("123");
        item.setDescription("Test");
        item.setAmount(1);
        Warehouse warehouse = new Warehouse();
        warehouse.setName("Test");
        item.setWarehouse(warehouse);
        itemList.add(item);

        String jsonString = jsonFormatter.convertForWarehouseReports(itemList);

        JSONObject jsonObject = new JSONObject(jsonString);

        JSONArray jsonArray = jsonObject.getJSONArray("Amount of items");

        JSONObject jsonObject1 = jsonArray.getJSONObject(0);

        assertEquals("123", jsonObject1.getString("SKU"));
        assertEquals("Test", jsonObject1.getString("Description"));
        assertEquals(1, jsonObject1.getInt("Amount"));
        assertEquals("Test", jsonObject1.getString("Warehouse"));
    }

    @Test
    @DisplayName("Should write json to file when the type of report is document report")
    void writeJsonToFileWhenTypeOfReportIsDocumentReport() {
        String jsonStr =
                new JSONObject()
                        .put("Document type", "Receipt")
                        .put("Date", "2020-05-05")
                        .put("Number", "1")
                        .put("Warehouse", "Warehouse 1")
                        .put(
                                "Items",
                                new JSONArray()
                                        .put(
                                                new JSONObject()
                                                        .put("SKU", "1")
                                                        .put("Description", "Item 1")
                                                        .put("Amount", "1")
                                                        .put("Last buy price", "1.0")))
                        .toString();

        String typeOfReport = "Document report";

        String fileName = jsonFormatter.writeJsonToFile(jsonStr, typeOfReport);

        assertEquals("jsonDocReport.json", fileName);
    }

    @Test
    @DisplayName("Should write json to file when the type of report is warehouse report")
    void writeJsonToFileWhenTypeOfReportIsWarehouseReport() {
        // Arrange
        List<Item> itemList = new ArrayList<>();
        Item item = new Item();
        item.setSku("123");
        item.setDescription("test");
        item.setAmount(1);
        Warehouse warehouse = new Warehouse();
        warehouse.setName("test");
        item.setWarehouse(warehouse);
        itemList.add(item);

        JSONArray jsonArray = new JSONArray();
        for (Item item1 : itemList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("SKU", item1.getSku());
            jsonObject.put("Description", item1.getDescription());
            jsonObject.put("Amount", item1.getAmount());
            jsonObject.put("Warehouse", item1.getWarehouse().getName());
            jsonArray.put(jsonObject);
        }
        JSONObject completedJson = new JSONObject();

        // Act
        completedJson.put("Amount of items", jsonArray);

        String fileName = "jsonWarehouseReport.json";

        // Assert
        assertEquals(
                fileName,
                jsonFormatter.writeJsonToFile(completedJson.toString(), "Warehouse report"));
    }

    @Test
    @DisplayName("Should write json to file when the type of report is item report")
    void writeJsonToFileWhenTypeOfReportIsItemReport() {
        // Arrange
        List<Item> itemList = new ArrayList<>();
        Item item = new Item();
        item.setSku("123");
        item.setDescription("Test");
        item.setLastBuyPrice(10.0);
        item.setLastSellPrice(20.0);
        item.setAmount(10);
        Warehouse warehouse = new Warehouse();
        warehouse.setName("Test");
        item.setWarehouse(warehouse);

        itemList.add(item);

        JSONArray jsonArray = new JSONArray();

        for (Item item1 : itemList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("SKU", item1.getSku());
            jsonObject.put("Description", item1.getDescription());
            jsonObject.put("Last buy price", item1.getLastBuyPrice());
            jsonObject.put("Last sell price", item1.getLastSellPrice());
            jsonObject.put("Warehouse", item1.getWarehouse().getName());
            jsonArray.put(jsonObject);
        }
        JSONObject completedJson = new JSONObject();

        // Act
        completedJson.put("Found items", jsonArray);

        String fileName = "jsonItemReport.json";

        // Assert
        assertEquals(
                fileName,
                jsonFormatter.writeJsonToFile(completedJson.toString(), "Item report"));
    }

    @Test
    @DisplayName("Should return a json string with the correct format for item reports")
    void convertForItemReportsShouldReturnJsonStringWithCorrectFormat() throws IOException {
        // Arrange
        Item item = new Item();
        item.setSku("123");
        item.setDescription("Test");
        item.setLastBuyPrice(10.0);
        item.setLastSellPrice(20.0);
        item.setAmount(10);

        Warehouse warehouse = new Warehouse();
        warehouse.setName("Test warehouse");

        item.setWarehouse(warehouse);

        List<Item> itemList = new ArrayList<>();
        itemList.add(item);

        // Act
        String result = jsonFormatter.convertForItemReports(itemList);

        JSONObject jsonObject = new JSONObject(result);
        JSONArray jsonArray = jsonObject.getJSONArray("Found items");

        // Assert
        assertEquals("123", jsonArray.getJSONObject(0).getString("SKU"));
        assertEquals("Test", jsonArray.getJSONObject(0).getString("Description"));
        assertEquals(10, jsonArray.getJSONObject(0).getDouble("Last buy price"));
        assertEquals(20, jsonArray.getJSONObject(0).getDouble("Last sell price"));
        assertEquals("Test warehouse", jsonArray.getJSONObject(0).getString("Warehouse"));
    }
}