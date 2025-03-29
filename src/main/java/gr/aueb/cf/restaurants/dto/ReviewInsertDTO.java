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
public class ReviewInsertDTO {

    @Schema(description = "The comment for the restaurant.", example = "A very good comment")
    private String comment;
    @Schema(description = "The username of the customer that left the comment.", example = "username")
    private String username;
    @Schema(description = "The restaurant's Id for that comment.", example = "10")
    private Long restaurantId;
}
