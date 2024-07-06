package gr.aueb.cf.restaurants.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewReadOnlyDTO {
    private Long reviewId;
    private String comment;
    private String username;
    private Long restaurantId;
}
