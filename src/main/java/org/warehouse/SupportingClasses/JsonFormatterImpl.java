package org.warehouse.SupportingClasses;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.warehouse.AccountingDocs.Entity.Movement;
import org.warehouse.AccountingDocs.Entity.Receipt;
import org.warehouse.AccountingDocs.Entity.Selling;
import org.warehouse.AccountingDocs.Entity.WriteOff;
import org.warehouse.Items.Entity.Item;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Service class, which converts data in multiple views, depends on business requirements
 */
@Service
public class JsonFormatterImpl implements JsonFormatter {

    /**
     * Method, which converts data for multiple item reports
     *
     * @param itemList - income items
     * @return - completed and formatted Json object
     * @throws IOException - if any I/O error occurs
     */
    @Override
    public String convertForItemReports(List<Item> itemList) throws IOException {
        JSONArray jsonArray = new JSONArray();

        for (Item item : itemList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("SKU", item.getSku());
            jsonObject.put("Description", item.getDescription());
            jsonObject.put("Last buy price", item.getLastBuyPrice());
            jsonObject.put("Last sell price", item.getLastSellPrice());
            jsonObject.put("Warehouse", item.getWarehouse().getName());
            jsonArray.put(jsonObject);
        }
        JSONObject completedJson = new JSONObject();

        completedJson.put("Found items", jsonArray);

        return completedJson.toString();
    }
    
    /**
     * Method, which write ready json to a file.
     * Name of file depends on a type of report.
     *
     * @param jsonStr - income ready json
     * @param typeOfReport - type of report
     * @return - a made file name
     */
    @Override
    public String writeJsonToFile(String jsonStr, String typeOfReport) {
        String fileName = new String();

        switch (typeOfReport) {
            case "Item report":
                fileName = "jsonItemReport.json";
                break;
            case "Warehouse report":
                fileName = "jsonWarehouseReport.json";
                break;
            case "Document report":
                fileName = "jsonDocReport.json";
                break;
        }
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(jsonStr);

            return fileName;
        }
        catch (IOException ex) {
            throw new RuntimeException("Internal error: " + ex.getMessage()
                    + ". It is necessary to change a returned file name in a source");
        }
    }

    /**
     * Method, which converts data for multiple warehouse reports
     *
     * @param itemList - income items
     * @return - completed and formatted Json object
     * @throws IOException - if any I/O error occurs
     */
    @Override
    public String convertForWarehouseReports(List<Item> itemList) {
        JSONArray jsonArray = new JSONArray();
        for (Item item : itemList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("SKU", item.getSku());
            jsonObject.put("Description", item.getDescription());
            jsonObject.put("Amount", item.getAmount());
            jsonObject.put("Warehouse", item.getWarehouse().getName());
            jsonArray.put(jsonObject);
        }
        JSONObject completedJson = new JSONObject();

        completedJson.put("Amount of items", jsonArray);

        return completedJson.toString();
    }

    /**
     * Method, which converts data for returning searched Receipt
     *
     * @param receipt - income Receipt object, which is searched
     * @return - completed and formatted Json object
     */
    @Override
    public String convertForReceipt(Receipt receipt) {
        nullCheckedForDocuments(receipt);

        JSONObject jsonToReturn = new JSONObject();

        jsonToReturn.put("Document type", "Receipt");
        jsonToReturn.put("Date", receipt.getReceiptDate());
        jsonToReturn.put("Number", receipt.getNumber());
        jsonToReturn.put("Warehouse", receipt.getWarehouse().getName());

        JSONArray jsonArray = new JSONArray();
        Set<Item> itemSet = receipt.getItemSet();

        for (Item item : itemSet) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("SKU", item.getSku());
            jsonObject.put("Description", item.getDescription());
            jsonObject.put("Amount", item.getAmount());
            jsonObject.put("Last buy price", item.getLastBuyPrice());
            jsonArray.put(jsonObject);
        }

        jsonToReturn.put("Items", jsonArray);

        return jsonToReturn.toString();
    }

    /**
     * Method, which converts data for returning searched Selling
     *
     * @param selling - income Selling object, which is searched
     * @return - completed and formatted Json object
     */
    @Override
    public String convertForSelling(Selling selling) {
        nullCheckedForDocuments(selling);

        JSONObject jsonToReturn = new JSONObject();

        jsonToReturn.put("Document type", "Selling");
        jsonToReturn.put("Date", selling.getSellingDate());
        jsonToReturn.put("Number", selling.getNumber());
        jsonToReturn.put("Warehouse", selling.getWarehouse().getName());

        JSONArray jsonArray = new JSONArray();
        Set<Item> itemSet = selling.getItemSet();

        for (Item item : itemSet) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("SKU", item.getSku());
            jsonObject.put("Description", item.getDescription());
            jsonObject.put("Amount", item.getAmount());
            jsonObject.put("Last sell price", item.getLastSellPrice());
            jsonArray.put(jsonObject);
        }

        jsonToReturn.put("Items", jsonArray);

        return jsonToReturn.toString();
    }

    /**
     * Method, which converts data for returning searched Movement
     *
     * @param movement - income Movement object, which is searched
     * @return - completed and formatted Json object
     */
    @Override
    public String convertForMovement(Movement movement) {
        nullCheckedForDocuments(movement);

        JSONObject jsonToReturn = new JSONObject();

        jsonToReturn.put("Document type", "Movement");
        jsonToReturn.put("Date", movement.getMovementDate());
        jsonToReturn.put("Number", movement.getNumber());
        jsonToReturn.put("From", movement.getWarehouseFrom().getName());
        jsonToReturn.put("To", movement.getWarehouseTo().getName());

        JSONArray jsonArray = new JSONArray();
        Set<Item> itemSet = movement.getItemSet();

        for (Item item : itemSet) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("SKU", item.getSku());
            jsonObject.put("Description", item.getDescription());
            jsonObject.put("Amount", item.getAmount());
            jsonArray.put(jsonObject);
        }

        jsonToReturn.put("Items", jsonArray);

        return jsonToReturn.toString();
    }

    /**
     * Method, which converts data for returning searched WriteOff
     *
     * @param writeOff - income WriteOff object, which is searched
     * @return - completed and formatted Json object
     */
    @Override
    public String convertForWriteOff(WriteOff writeOff) {
        nullCheckedForDocuments(writeOff);

        JSONObject jsonToReturn = new JSONObject();

        jsonToReturn.put("Document type", "Write-off");
        jsonToReturn.put("Date", writeOff.getWriteOffDate());
        jsonToReturn.put("Number", writeOff.getNumber());
        jsonToReturn.put("Warehouse", writeOff.getWarehouse().getName());

        JSONArray jsonArray = new JSONArray();
        Set<Item> itemSet = writeOff.getItemSet();

        for (Item item : itemSet) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("SKU", item.getSku());
            jsonObject.put("Description", item.getDescription());
            jsonObject.put("Amount", item.getAmount());
            jsonArray.put(jsonObject);
        }

        jsonToReturn.put("Items", jsonArray);

        return jsonToReturn.toString();
    }

    /**
     * Helping method, which checks incoming document on possible null value
     *
     * @param document - incoming document
     */
    private void nullCheckedForDocuments(Object document) {
        if (document == null) {
            throw new RuntimeException("Document is not found");
        }
    }
}
