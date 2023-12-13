package gr.aueb.cf.restaurants.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationReadOnlyDTO {
    private String restaurantName;
    private String reservationDate;
    private String status;
    private Long partySize;
}
