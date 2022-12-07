package org.warehouse.SupportingClasses;

import org.springframework.stereotype.Service;
import org.warehouse.Items.DTO.ItemDTO;

import java.util.List;

/**
 * Interface for custom validation of incoming documents
 */
@Service
public interface Validator {

    void checkDocNumber(String docName, Integer docNumber);

    void checkItemsInDoc(List<ItemDTO> items, String typeDoc);
}
