package warehouse.AccountingDocs.Service;

import org.springframework.stereotype.Service;
import org.warehouse.AccountingDocs.DTO.SellingDTO;

/**
 * Functional interface for working with selling docs.
 */
@Service
public interface SellingService {

    public void createNewSelling(SellingDTO sellingDTO);
}
