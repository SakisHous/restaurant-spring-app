package gr.aueb.cf.restaurants.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponseDTO {
    private String restaurantName;
    private String reservationDate;
    private String status;
    private Long partySize;
}
