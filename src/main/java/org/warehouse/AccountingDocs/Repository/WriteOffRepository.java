package org.warehouse.AccountingDocs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.warehouse.AccountingDocs.Entity.WriteOff;

/**
 * Interface for working with write-off docs in a database
 */
@Repository
public interface WriteOffRepository extends JpaRepository<WriteOff, Long> {

    public WriteOff findWriteOffByNumber(Integer number);
}
