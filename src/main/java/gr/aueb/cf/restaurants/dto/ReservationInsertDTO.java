package gr.aueb.cf.restaurants.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationInsertDTO {
    private String restaurantId;
    private Date reservationDate;
    private Long partySize;
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
