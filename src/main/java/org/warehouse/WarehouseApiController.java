package warehouse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.warehouse.AccountingDocs.DTO.MovementDTO;
import org.warehouse.AccountingDocs.DTO.ReceiptDTO;
import org.warehouse.AccountingDocs.DTO.SellingDTO;
import org.warehouse.AccountingDocs.DTO.WriteOffDTO;
import org.warehouse.AccountingDocs.Entity.Movement;
import org.warehouse.AccountingDocs.Entity.Receipt;
import org.warehouse.AccountingDocs.Entity.Selling;
import org.warehouse.AccountingDocs.Entity.WriteOff;
import org.warehouse.AccountingDocs.Repository.MovementRepository;
import org.warehouse.AccountingDocs.Repository.ReceiptRepository;
import org.warehouse.AccountingDocs.Repository.SellingRepository;
import org.warehouse.AccountingDocs.Repository.WriteOffRepository;
import org.warehouse.AccountingDocs.Service.MovementService;
import org.warehouse.AccountingDocs.Service.ReceiptService;
import org.warehouse.AccountingDocs.Service.SellingService;
import org.warehouse.AccountingDocs.Service.WriteOffService;
import org.warehouse.Items.Entity.Item;
import org.warehouse.Items.Repository.ItemRepository;
import org.warehouse.Items.Service.ItemServiceImpl;
import org.warehouse.SupportingClasses.JsonFormatter;
import org.warehouse.SupportingClasses.Validator;
import org.warehouse.Warehouses.Service.WarehouseService;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

/**
 * Main api controller class
 */
@Validated
@RestController
@Api(description = "API for working with documents and items in a warehouse")
@RequestMapping("/api/myWarehouse")
public class WarehouseApiController {

    ItemServiceImpl itemService;
    WarehouseService warehouseService;
    ReceiptRepository receiptRepository;
    SellingRepository sellingRepository;
    MovementRepository movementRepository;
    WriteOffRepository writeOffRepository;
    ReceiptService receiptService;
    SellingService sellingService;
    WriteOffService writeOffService;
    MovementService movementService;
    Validator validator;
    JsonFormatter jsonFormatter;
    ItemRepository itemRepository;

    public WarehouseApiController(ItemServiceImpl itemService, WarehouseService warehouseService,
                                  ReceiptRepository receiptRepository, SellingRepository sellingRepository,
                                  MovementRepository movementRepository, WriteOffRepository writeOffRepository,
                                  ReceiptService receiptService, SellingService sellingService,
                                  WriteOffService writeOffService, MovementService movementService,
                                  Validator validator, JsonFormatter jsonFormatter, ItemRepository itemRepository) {
        this.itemService = itemService;
        this.warehouseService = warehouseService;
        this.receiptRepository = receiptRepository;
        this.sellingRepository = sellingRepository;
        this.movementRepository = movementRepository;
        this.writeOffRepository = writeOffRepository;
        this.receiptService = receiptService;
        this.sellingService = sellingService;
        this.writeOffService = writeOffService;
        this.movementService = movementService;
        this.validator = validator;
        this.jsonFormatter = jsonFormatter;
        this.itemRepository = itemRepository;
    }


    /**
     * GET method, which takes SKU of item and returns information about it:
     * SKU, description, last buy price, last sell price in JSON format.
     * If item is not found, method returns an empty list
     *
     * @param sku - SKU of searched item
     * @return - simple body in JSON format with information
     * @throws IOException - if any Input/Output exception occurs
     */
    @ApiOperation(value = "Get info about item by its SKU",
            notes = "Method returns information about item:\n" +
                    "SKU, description, last buy price, last sell price in JSON format.\n " +
                    "If item is not found, method returns an empty list.")

    @ApiResponse(code = 406, message = "Not acceptable")

    @GetMapping(path = "/getItem" , produces = {"application/json"})
    public ResponseEntity<Object> getItem (@Param("sku")
                                               @ApiParam(name = "sku",
                                                       value = "SKU of item",
                                                       example = "267s19",
                                                       required = true) String sku) throws IOException {
        return ResponseEntity.ok()
                .body(jsonFormatter.convertForItemReports(itemRepository.findAllBySku(sku)));
    }


    /**
     * GET method, which returns a JSON file with report about all existing items in all warehouses.
     * Information about items in report: SKU, description, last buy price, last sell price
     *
     * @return - a JSON file with a report
     * @throws IOException - if any Input/Output exception occurs
     */
    @ApiOperation(value = "Get all items in all warehouses",
            notes = "Method returns a JSON file with report about all existing items in all warehouses.\n" +
                    "Information about items in report: SKU, description, last buy price, last sell price " +
                    "of each item.\n Report is automatically downloaded with a request." )

    @ApiResponse(code = 406, message = "Not acceptable")
    @GetMapping(path = "/getAllExistingItems/downloadReport", produces = {"application/json"})
    public ResponseEntity<Object> getAllItems() throws IOException {
        List<Item> itemList = itemService.getSortedAllItemsFromWarehouses();

        String filename = jsonFormatter.writeJsonToFile(
                jsonFormatter.convertForItemReports(itemList), "Item report");

        return getFileToResponse(filename);
    }

    /**
     * GET method, which returns a JSON file with report about balance of all existing items in all warehouses.
     * Information about items in report: SKU, description, amount, warehouse.
     *
     * @return - a JSON file with a report
     * @throws IOException - if any Input/Output exception occurs
     */
    @ApiOperation(value = "Get balance of all items in all warehouses",
            notes = "Method returns a JSON file with report about balance of all existing items in all warehouses.\n" +
                    "Information about items in report: SKU, description, amount, warehouse name " +
                    "of each item.\n Report is automatically downloaded with a request." )

    @ApiResponse(code = 406, message = "Not acceptable")
    @GetMapping(path = "/getBalanceOfItems/downloadReport", produces = {"application/json"})
    public ResponseEntity<Object> getBalanceOfItemsInAllWarehouses() throws IOException {
        List<Item> itemList = itemService.getSortedAllItemsFromWarehouses();

        String filename = jsonFormatter.writeJsonToFile(
                jsonFormatter.convertForWarehouseReports(itemList), "Warehouse report");

        return getFileToResponse(filename);

    }

    /**
     * GET method, which takes a warehouse name and returns a JSON file with report about balance
     * of all existing items in this warehouse.
     * Information about items in report: SKU, description, amount
     *
     * @param warehouseName - warehouse name
     * @return - a JSON file with a report
     * @throws IOException - if any Input/Output exception occurs
     */
    @ApiOperation(value = "Get balance of all items in particular warehouse",
            notes = "Method returns a JSON file with report about balance of all existing items in particular warehouse.\n" +
                    "Information about items in report: SKU, description, amount " +
                    "of each item.\n Report is automatically downloaded with a request." )

    @ApiResponse(code = 406, message = "Not acceptable")
    @GetMapping(path = "/getBalanceInWarehouse/downloadReport", produces = {"application/json"})
    public ResponseEntity<Object> getBalanceOfItemsInAllWarehouses(
            @Param("warehouseName")
            @ApiParam(name = "warehouseName",
                    value = "Warehouse name",
                    example = "Novosibirsk_1",
                    required = true) String warehouseName) throws IOException {
        List<Item> itemList = warehouseService.getListOfItemsInWarehouse(warehouseName);

        String filename = jsonFormatter.writeJsonToFile(
                jsonFormatter.convertForWarehouseReports(itemList), "Warehouse report");

        return getFileToResponse(filename);

    }

    /**
     * GET method, which takes number and type of searched document and returns it in JSON file.
     * If document is not found in a database or type of document is incorrect
     * or number is not correct, method throws Runtime exception or NumberFormatException.
     *
     * @param typeDoc - type of document
     * @param number - number of document
     * @return - a found document in a JSON file
     * @throws IOException - if any Input/Output exception occurs
     */
    @ApiOperation(value = "Get particular document, which is being stored in a warehouse",
            notes = "Method returns a document as a JSON file with all data, which was in it, " +
                    "when it was saved in warehouse\n" + "Document is automatically downloaded with a request.")

    @ApiResponse(code = 406, message = "Not acceptable")
    @GetMapping(path = "/getDocument/download", produces = {"application/json"})
    public ResponseEntity<Object> getBalanceOfItemsInAllWarehouses(
            @Param("typeDoc")
            @ApiParam(name = "typeDoc",
                    value = "Type of document: receipt / selling / movement / writeOff",
                    example = "receipt",
                    required = true) String typeDoc,
            @Param("number")
            @ApiParam(name = "number",
                    value = "Number of document",
                    example = "144",
                    required = true) Integer number) throws IOException {

        String filename = null;

        switch (typeDoc) {
            case "receipt":
                Receipt receipt = receiptRepository.findReceiptByNumber(number);
                filename = jsonFormatter.writeJsonToFile(
                        jsonFormatter.convertForReceipt(receipt), "Document report");
                break;
            case "selling":
                Selling selling = sellingRepository.findSellingByNumber(number);
                filename = jsonFormatter.writeJsonToFile(
                        jsonFormatter.convertForSelling(selling), "Document report");
                break;
            case "movement":
                Movement movement = movementRepository.findMovementByNumber(number);
                filename = jsonFormatter.writeJsonToFile(
                        jsonFormatter.convertForMovement(movement), "Document report");
                break;
            case "writeOff":
                WriteOff writeOff = writeOffRepository.findWriteOffByNumber(number);
                filename = jsonFormatter.writeJsonToFile(
                        jsonFormatter.convertForWriteOff(writeOff), "Document report");
                break;
            default:
                throw new RuntimeException("Incorrect doc type");
        }
        return getFileToResponse(filename);
    }

    /**
     * Helping method, which takes a file name in a source and creates a JSON file to be returned with response.
     *
     * @param filename - name of a file in a source
     * @return - created JSON file
     * @throws IOException - if any Input/Output exception occurs
     */
    private ResponseEntity<Object> getFileToResponse(String filename) throws IOException {
        File file = new File(filename);
        InputStreamResource resource = new InputStreamResource(Files.newInputStream(file.toPath()));
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));

        return ResponseEntity.ok().headers(headers).contentLength(
        file.length()).contentType(MediaType.parseMediaType("application/json")).body(resource);
    }

    /**
     * POST method, which takes a receipt document in JSON format and add items from it in a warehouse.
     * Then receipt is saved in a database.
     * Method, at first, validates incoming document.
     *
     * @param receiptDTO - DTO object in incoming receipt document
     */
    @ApiOperation(value = "Make new receipt and add items in a warehouse",
            notes = "Method takes a receipt document in JSON format and add items from it in a warehouse.\n" +
                    "Then receipt is saved in a database.\n\n" +
                    "Important! Document shouldn't contain a sell price.\n" +
                    "Before creating it is need to take a look on a ReceiptDTO model at the end of this documentation.")

    @ApiResponse(code = 406, message = "Not acceptable")
    @PostMapping(path = "/makeReceipt", consumes= {MediaType.APPLICATION_JSON_VALUE})
    public void postReceipt(@Valid @RequestBody ReceiptDTO receiptDTO) {
        validator.checkDocNumber(receiptDTO.getTypeDoc(), receiptDTO.getNumber());
        validator.checkItemsInDoc(receiptDTO.getItemList(), receiptDTO.getTypeDoc());
        receiptService.createNewReceipt(receiptDTO);
    }

    /**
     * PUT method, which takes a selling document in JSON format, sets a new sell price
     * and reduces items in a warehouse on amount, which specified in document.
     * Then selling is saved in a database.
     * Method, at first, validates incoming document.
     *
     * @param sellingDTO- DTO object in incoming selling document
     */
    @ApiOperation(value = "Make new selling and reduce items from a warehouse",
            notes = "Method takes a selling document in JSON format, sets a new sell price\n" +
                    "and reduces items in a warehouse on amount, which specified in document.\n" +
                    "Then selling is saved in a database.\n\n" +
                    "Important! Document shouldn't contain a buy price.\n" +
                    "Before creating it is need to take a look on a SellingDTO model at the end of this documentation.")

    @ApiResponse(code = 406, message = "Not acceptable")
    @PutMapping(path = "/makeSelling", consumes= {MediaType.APPLICATION_JSON_VALUE})
    public void patchSelling(@Valid @RequestBody SellingDTO sellingDTO) {
        validator.checkDocNumber(sellingDTO.getTypeDoc(), sellingDTO.getNumber());
        validator.checkItemsInDoc(sellingDTO.getItemList(), sellingDTO.getTypeDoc());
        sellingService.createNewSelling(sellingDTO);
    }

    /**
     * PUT method, which takes a movement document in JSON format
     * and moves items from one warehouse to another on an amount, which specified in document.
     * Method doesn't set new buy or sale price.
     * After that, a movement document is saved in a database.
     * Method, at first, validates incoming document.
     *
     * @param movementDTO - DTO object in movement document
     */
    @ApiOperation(value = "Make new movement and move items between warehouses",
            notes = "Method takes a movement document in JSON format\n" + "and moves items from one warehouse " +
                    "to another on an amount, which specified in document.\n" +
                    "After that, a movement document is saved in a database.\n\n" +
                    "Important! Document shouldn't contain a buy or a sell price.\n" +
                    "Before creating it is need to take a look on a SellingDTO model at the end of this documentation.")

    @ApiResponse(code = 406, message = "Not acceptable")
    @PutMapping(path = "/makeMovement", consumes= {MediaType.APPLICATION_JSON_VALUE})
    public void patchWriteOff(@Valid @RequestBody MovementDTO movementDTO) {
        if (Objects.equals(movementDTO.getWarehouseFrom(), movementDTO.getWarehouseTo())) {
            throw new RuntimeException("Warehouses must be different");
        }
        validator.checkDocNumber(movementDTO.getTypeDoc(), movementDTO.getNumber());
        validator.checkItemsInDoc(movementDTO.getItemList(), movementDTO.getTypeDoc());
        movementService.createNewMovement(movementDTO);
    }

    /**
     * DELETE method, which takes a write-off document in JSON format
     * and reduces items in a warehouse on an amount, which specified in document.
     * This document doesn't sale items, only writes-off, so that new sell price is not set.
     * Then write-off is saved in a database.
     * Method, at first, validates incoming document.
     *
     * @param writeOffDTO - DTO object in write-off document
     */
    @ApiOperation(value = "Make new write-off and reduce items from a warehouse without selling",
            notes = "Method takes a write-off document in JSON format and reduces items in a warehouse on an amount, " +
                    "which specified in document.\n" +
                    "This document doesn't sale items, only writes-off, so that the new sell price is not set.\n" +
                    "Then write-off is saved in a database.\n\n" +
                    "Important! Document shouldn't contain a buy price and sell price.\n" +
                    "Before creating it is need to take a look on a WriteOffDTO model at the end of this documentation")

    @ApiResponse(code = 406, message = "Not acceptable")
    @DeleteMapping(path = "/makeWriteOff", consumes= {MediaType.APPLICATION_JSON_VALUE})
    public void patchWriteOff(@Valid @RequestBody WriteOffDTO writeOffDTO) {
        validator.checkDocNumber(writeOffDTO.getTypeDoc(), writeOffDTO.getNumber());
        validator.checkItemsInDoc(writeOffDTO.getItemList(), writeOffDTO.getTypeDoc());
        writeOffService.createNewWriteOff(writeOffDTO);
    }

}
