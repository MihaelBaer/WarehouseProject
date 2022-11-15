package warehouse.AccountingDocs.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.warehouse.Items.DTO.ItemDTO;

import javax.validation.constraints.*;
import java.util.List;

/**
 * DTO class foe usage in selling process
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SellingDTO {

    @NonNull
    @ApiModelProperty(notes = "Type of document", example = "Selling", required = true)
    @Pattern(regexp = "Selling")
    private String typeDoc;

    @NonNull
    @Positive
    @ApiModelProperty(notes = "Selling number", example = "230", required = true)
    private Integer number;

    @NonNull
    @ApiModelProperty(notes = "Selling date", example = "09-11-2022", required = true)
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$")
    private String sellingDate;

    @ApiModelProperty(notes = "Warehouse name", example = "Novosibirsk_1", required = true)
    @NotBlank
    private String warehouseName;

    @NonNull
    @NotEmpty
    private List<ItemDTO> itemList;
}
