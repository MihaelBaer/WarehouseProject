package warehouse.AccountingDocs.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.warehouse.Items.DTO.ItemDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * DTO class for usage in movement process
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MovementDTO {

    @NonNull
    @ApiModelProperty(notes = "Type of document", example = "Movement", required = true)
    @Pattern(regexp = "Movement")
    private String typeDoc;

    @NonNull
    @ApiModelProperty(notes = "Movement number", example = "341", required = true)
    private Integer number;

    @NonNull
    @ApiModelProperty(notes = "Movement date", example = "03-11-2022", required = true)
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$")
    private String movementDate;

    @NotBlank
    @ApiModelProperty(notes = "Warehouse from name", example = "Moscow_1", required = true)
    private String warehouseFrom;

    @NotBlank
    @ApiModelProperty(notes = "Warehouse to name", example = "Novosibirsk_1", required = true)
    private String warehouseTo;

    @NotEmpty
    @NonNull
    private List<ItemDTO> itemList;
}
