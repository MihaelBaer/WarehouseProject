package org.warehouse.AccountingDocs.Service;

import org.springframework.stereotype.Service;
import org.warehouse.AccountingDocs.DTO.MovementDTO;

/**
 * Functional interface for working with movement docs.
 */
@Service
public interface MovementService {

    public void createNewMovement(MovementDTO movementDTO);
}
