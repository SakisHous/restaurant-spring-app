package gr.aueb.cf.restaurants.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "RESERVATION")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVATION_ID")
    private Long reservationId;

    @Column(name = "RESERVATION_DATE", nullable = false)
    private Date reservationDate;

    @Column(name = "PARTY_SIZE", nullable = false)
    private Long partySize;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurantId", nullable = false)
    @JsonIgnore
    private Restaurant restaurant;

    public Reservation() {
    }

    public Reservation(Long reservationId, Date reservationDate, Long partySize, Status status) {
        this.reservationId = reservationId;
        this.reservationDate = reservationDate;
        this.partySize = partySize;
        this.status = status;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
