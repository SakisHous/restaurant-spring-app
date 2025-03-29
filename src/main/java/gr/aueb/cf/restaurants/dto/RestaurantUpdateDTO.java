package gr.aueb.cf.restaurants.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantUpdateDTO {

    @Schema(description = "The restaurant's Id to be updated.", example = "10")
    private Long restaurantId;
    @Schema(description = "The new name of the restaurant.", example = "New Restaurant Name")
    private String name;
    @Schema(description = "The new restaurant's address.", example = "7th Avenue")
    private String address;
    @Schema(description = "The new phone number of restaurant's service.", example = "123456789")
    private String contactNumber;
    @Schema(description = "The new city of the restaurant.", example = "New Jersey")
    private String city;
}
