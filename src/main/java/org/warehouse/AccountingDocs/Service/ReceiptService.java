package org.warehouse.AccountingDocs.Service;

import org.springframework.stereotype.Service;
import org.warehouse.AccountingDocs.DTO.ReceiptDTO;

/**
 * Functional interface for working with receipt documents
 */
@Service
public interface ReceiptService {
    public void createNewReceipt(ReceiptDTO receiptDTO);

}
