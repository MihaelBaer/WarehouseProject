package org.warehouse.SupportingClasses;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.warehouse.AccountingDocs.Repository.MovementRepository;
import org.warehouse.AccountingDocs.Repository.ReceiptRepository;
import org.warehouse.AccountingDocs.Repository.SellingRepository;
import org.warehouse.AccountingDocs.Repository.WriteOffRepository;
import org.warehouse.Items.DTO.ItemDTO;

import java.util.List;
import java.util.Objects;

/**
 * Service class form custom validation of incoming documents
 */
@Service
public class ValidatorImpl implements Validator {

    ReceiptRepository receiptRepository;
    SellingRepository sellingRepository;
    WriteOffRepository writeOffRepository;
    MovementRepository movementRepository;

    public ValidatorImpl(ReceiptRepository receiptRepository,
                         SellingRepository sellingRepository,
                         WriteOffRepository writeOffRepository,
                         MovementRepository movementRepository) {
        this.receiptRepository = receiptRepository;
        this.sellingRepository = sellingRepository;
        this.writeOffRepository = writeOffRepository;
        this.movementRepository = movementRepository;
    }

    /**
     * Method, which checks number of incoming document.
     * Number for certain doc type must be unique.
     * If such number is already exists in a database, method throws RuntimeException.
     *
     * @param docName - type of document
     * @param docNumber - document number
     */
    @Override
    public void checkDocNumber(String docName, Integer docNumber) {
        if (Objects.equals(docName, "Receipt") && (receiptRepository.findReceiptByNumber(docNumber) != null)) {
                throw new RuntimeException("This number of receipt already exists. " +
                        "Try again with another receipt number");
        }
        if (Objects.equals(docName, "Selling") && (sellingRepository.findSellingByNumber(docNumber) != null)) {
                throw new RuntimeException("This number of selling already exists. " +
                        "Try again with another selling number");
        }
        if (Objects.equals(docName, "Write-off") && (writeOffRepository.findWriteOffByNumber(docNumber) != null)) {
                throw new RuntimeException("This number of write-off already exists. " +
                        "Try again with another write-off number");
        }
        if (Objects.equals(docName, "Movement") && (movementRepository.findMovementByNumber(docNumber) != null)) {
                throw new RuntimeException("This number of movement already exists. " +
                        "Try again with another movement number");
        }
    }

    /**
     * Method checks items in an incoming document.
     * The number of checks depends on a doc type
     * Checked properties: sku, description, amount, last sell price (for selling), last buy price (for receipt).
     * Method uses helping methods.
     *
     * @param items - list of items in document
     * @param typeDoc - type of incoming document
     */
    @Override
    public void checkItemsInDoc(List<ItemDTO> items, String typeDoc) {
        for (ItemDTO itemDTO : items) {
            checkSku(itemDTO);
            checkDescription(itemDTO);
            checkAmount(itemDTO);
            if (Objects.equals(typeDoc, "Movement")
                    || Objects.equals(typeDoc, "Write-off")
                    || Objects.equals(typeDoc,"Removing")) {
                checkUnnecessaryBuyPrice(itemDTO, typeDoc);
                checkUnnecessarySellPrice(itemDTO, typeDoc);
            }

            if (Objects.equals(typeDoc, "Receipt")) {
                checkLastBuyPrice(itemDTO);
                checkUnnecessarySellPrice(itemDTO, typeDoc);
            }
            if ((Objects.equals(typeDoc, "Selling"))) {
                checkLastSellPrice(itemDTO);
                checkUnnecessaryBuyPrice(itemDTO, typeDoc);
            }
        }
    }

    /**
     * Helping method, which checks sku of item
     *
     * @param itemDTO - item DTO object
     */
    private void checkSku(ItemDTO itemDTO) {
        if (StringUtils.isBlank(itemDTO.getSku())
                || (itemDTO.getSku().length() < 3)
                || (itemDTO.getSku().length() > 10)) {
            throw new RuntimeException("Specified incorrect sku: " + itemDTO.getSku()
                    + " Must be from 3 to 10 characters");
        }
    }

    /**
     * Helping method, which checks description of item
     *
     * @param itemDTO - item DTO object
     */
    private void checkDescription(ItemDTO itemDTO) {
        if (StringUtils.isBlank(itemDTO.getDescription())) {
            throw new RuntimeException("Description of item is empty: " + itemDTO.getSku());
        }
    }

    /**
     * Helping method, which checks last buy price of item
     *
     * @param itemDTO - item DTO object
     */
    private void checkLastBuyPrice(ItemDTO itemDTO) {
        if (itemDTO.getLastBuyPrice() == null || itemDTO.getLastBuyPrice() < 0) {
            throw new RuntimeException("Last buy price must be positive or zero: " + itemDTO.getDescription());
        }
    }

    /**
     * Helping method, which checks last sell price of item
     *
     * @param itemDTO - item DTO object
     */
    private void checkLastSellPrice(ItemDTO itemDTO) {
        if (itemDTO.getLastSellPrice() == null || itemDTO.getLastSellPrice() < 0) {
            throw new RuntimeException("Last sell price must be positive or zero: " + itemDTO.getDescription());
        }
    }

    /**
     * Helping method, which checks last amount of item
     *
     * @param itemDTO - item DTO object
     */
    private void checkAmount(ItemDTO itemDTO) {
        if (itemDTO.getAmount() == null || itemDTO.getAmount() <= 0) {
            throw new RuntimeException("Amount of item must be greater than 0: " +  itemDTO.getDescription());
        }
    }

    /**
     * Helping method, which checks unnecessary buy price of item (for selling)
     *
     * @param itemDTO - item DTO object
     * @param typeDoc - type of document
     */
    private void checkUnnecessaryBuyPrice(ItemDTO itemDTO, String typeDoc) {
        if (itemDTO.getLastBuyPrice() != null) {
            throw new RuntimeException("Such document shouldn't contain buy price: " + typeDoc);
        }
    }

    /**
     * Helping method, which checks unnecessary sell price of item (for receipt)
     *
     * @param itemDTO - item DTO object
     * @param typeDoc - type of document
     */
    private void checkUnnecessarySellPrice(ItemDTO itemDTO, String typeDoc) {
        if (itemDTO.getLastSellPrice() != null) {
            throw new RuntimeException("Such document shouldn't contain sell price: " + typeDoc);
        }
    }
}
