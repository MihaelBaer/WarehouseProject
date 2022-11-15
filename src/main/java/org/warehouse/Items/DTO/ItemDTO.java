package warehouse.Items.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.warehouse.Warehouses.Entity.Warehouse;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * DTO class for working with items
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ItemDTO {

    @ApiModelProperty(notes = "SKU of item", example = "1877mn12", required = true)
    @NotBlank
    @Length(min = 3, max = 10)
    private String sku;

    @ApiModelProperty(notes = "Item description", example = "Oranges", required = true)
    @NotBlank
    private String description;

    @ApiModelProperty(notes = "Last sell price", example = "350.89", required = false)
    private Double lastBuyPrice;

    @ApiModelProperty(notes = "Last sell price", example = "120.50", required = false)
    private Double lastSellPrice;

    @ApiModelProperty(notes = "Amount of item", example = "120", required = true)
    @NotNull
    @Positive
    private Integer amount;

    @JsonIgnore
    private Warehouse warehouse;
}
