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
public class RestaurantResponseDTO {

    @Schema(description = "The restaurant's Id.", example = "1")
    private Long restaurantId;
    @Schema(description = "The restaurant's name.", example = "Restaurant Name")
    private String name;
    @Schema(description = "The restaurant's address.", example = "5th Avenue, NY")
    private String address;
    @Schema(description = "The restaurant's contact number.", example = "123456789")
    private String contactNumber;
    @Schema(description = "The restaurant's city.", example = "New York")
    private String city;
    @Schema(description = "The restaurant's menu description.", example = "Food: Pizza, Pastas, Italian cheeses and local sausages. Drinks: Beverages and juices")
    private String menuDescription;
    @Schema(description = "The restaurant's open hour.", example = "16:00")
    private String openHour;
    @Schema(description = "The restaurant's close hour.", example = "23:30")
    private String closeHour;
}
