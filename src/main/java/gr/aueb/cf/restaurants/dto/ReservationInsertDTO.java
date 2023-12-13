package gr.aueb.cf.restaurants.dto;

import java.util.Date;

public class ReservationInsertDTO {
    private String restaurantId;
    private Date reservationDate;
    private Long partySize;
    private String username;

    public ReservationInsertDTO() {
    }

    public ReservationInsertDTO(String restaurantId, Date reservationDate, Long partySize, String username) {
        this.restaurantId = restaurantId;
        this.reservationDate = reservationDate;
        this.partySize = partySize;
        this.username = username;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Long getPartySize() {
        return partySize;
    }

    public void setPartySize(Long partySize) {
        this.partySize = partySize;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "ReservationInsertDTO{" +
                "restaurantId='" + restaurantId + '\'' +
                ", reservationDate=" + reservationDate +
                ", partySize=" + partySize +
                '}';
    }
}
