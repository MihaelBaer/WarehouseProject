package org.warehouse.AccountingDocs.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.warehouse.Items.DTO.ItemDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * DTO class for usage in receipt process
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ReceiptDTO {

    @NonNull
    @ApiModelProperty(notes = "Type of document", example = "Receipt", required = true)
    @Pattern(regexp = "Receipt")
    private String typeDoc;

    @NonNull
    @ApiModelProperty(notes = "Receipt number", example = "180", required = true)
    private Integer number;

    @NonNull
    @ApiModelProperty(notes = "Receipt date", example = "10-11-2022", required = true)
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$")
    private String receiptDate;

    @ApiModelProperty(notes = "Warehouse name", example = "Moscow_1", required = true)
    @NotBlank
    private String warehouseName;

    @NonNull
    @NotEmpty
    private List<ItemDTO> itemList;
}
