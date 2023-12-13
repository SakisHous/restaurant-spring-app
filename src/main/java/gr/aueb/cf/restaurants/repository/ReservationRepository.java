package gr.aueb.cf.restaurants.repository;

import gr.aueb.cf.restaurants.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface defines the {@link Reservation} entity repository.
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r WHERE r.user.username = ?1")
    List<Reservation> getReservationsByUser(String username);
}
