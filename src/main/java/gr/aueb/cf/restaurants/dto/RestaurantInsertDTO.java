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
public class RestaurantInsertDTO {

    @Schema(description = "The restaurant to be inserted in the database.", example = "New Restaurant")
    private String name;
    @Schema(description = "The address of the restaurant.", example = "New Restaurant")
    private String address;
    @Schema(description = "The phone number of restaurant's service.", example = "123456789")
    private String contactNumber;
    @Schema(description = "The city where the restaurant is located.", example = "New York")
    private String city;
}
