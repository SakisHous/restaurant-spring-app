package gr.aueb.cf.restaurants.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationInsertDTO {

    @Schema(description="The restaurant's Id for the reservation.", example = "1")
    private String restaurantId;
    @Schema(description="The reservation date time in UTC format for the reservation.", example = "2025-01-01 12:03:00")
    private LocalDate reservationDate;
    @Schema(description="The number for the people in this reservation.", example = "4")
    private Integer partySize;
    @Schema(description="The username of the customer who made the reservation.", example = "username")
    private String username;

    @Override
    public String toString() {
        return "ReservationInsertDTO{" +
                "restaurantId='" + restaurantId + '\'' +
                ", reservationDate=" + reservationDate +
                ", partySize=" + partySize +
                '}';
    }
}
