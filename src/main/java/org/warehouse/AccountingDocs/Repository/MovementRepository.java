package warehouse.AccountingDocs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.warehouse.AccountingDocs.Entity.Movement;

/**
 * Interface for working with movement docs in a database
 */
@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {
    public Movement findMovementByNumber(Integer number);
}
