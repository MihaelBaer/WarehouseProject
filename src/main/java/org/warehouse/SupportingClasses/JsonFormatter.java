package warehouse.SupportingClasses;

import org.springframework.stereotype.Service;
import org.warehouse.AccountingDocs.Entity.Movement;
import org.warehouse.AccountingDocs.Entity.Receipt;
import org.warehouse.AccountingDocs.Entity.Selling;
import org.warehouse.AccountingDocs.Entity.WriteOff;
import org.warehouse.Items.Entity.Item;

import java.io.IOException;
import java.util.List;

/**
 * Interface, which converts data in multiple views, depends on business requirements
 */
@Service
public interface JsonFormatter {

    public String convertForItemReports(List<Item> itemList) throws IOException;

    public String convertForWarehouseReports(List<Item> itemList);

    public String convertForReceipt(Receipt receipt);

    public String convertForSelling(Selling selling);

    public String convertForMovement(Movement movement);

    public String convertForWriteOff(WriteOff writeOff);

    public String writeJsonToFile(String jsonString, String typeOfReport);
}
