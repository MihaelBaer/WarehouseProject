package org.warehouse.AccountingDocs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.warehouse.AccountingDocs.Entity.Receipt;

/**
 * Interface for working with receipt docs in a database
 */
@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    Receipt findReceiptByNumber(Integer number);
}
