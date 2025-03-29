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
public class ReviewResponseDTO {

    @Schema(description="The review Id.", example = "1")
    private Long reviewId;
    @Schema(description="The customer's comment in this review.", example = "Very good food and service")
    private String comment;
    @Schema(description="The username of the customer.", example = "password123")
    private String username;
    @Schema(description="The restaurant's Id for this review.", example = "password123")
    private Long restaurantId;
}
