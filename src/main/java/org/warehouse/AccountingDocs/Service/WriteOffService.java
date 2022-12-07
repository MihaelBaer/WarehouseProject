package org.warehouse.AccountingDocs.Service;

import org.springframework.stereotype.Service;
import org.warehouse.AccountingDocs.DTO.WriteOffDTO;

/**
 * Functional interface for working with write-off docs.
 */
@Service
public interface WriteOffService {

    public void createNewWriteOff(WriteOffDTO writeOffDTO);
}
