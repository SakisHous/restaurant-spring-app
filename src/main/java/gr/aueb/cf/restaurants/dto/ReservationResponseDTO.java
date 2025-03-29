package gr.aueb.cf.restaurants.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponseDTO {

    @Schema(description="The restaurant's name upon the reservation was made.", example = "Restaurant Name")
    private String restaurantName;
    @Schema(description = "The reservation date.", example = "18:30 04-01-2025")
    private String reservationDate;
    @Schema(description = "The status of the reservation.", example = "CANCELLED", allowableValues = "COMPLETED,PENDING,CANCELLED")
    private String status;
    @Schema(description = "The number of people.", example = "4")
    private Integer partySize;
}
