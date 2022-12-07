package org.warehouse.AccountingDocs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.warehouse.AccountingDocs.Entity.Selling;

/**
 * Interface for working with selling docs in a database
 */
@Repository
public interface SellingRepository extends JpaRepository<Selling, Long> {

    public Selling findSellingByNumber(Integer number);
}
