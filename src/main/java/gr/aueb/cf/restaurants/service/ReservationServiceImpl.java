package gr.aueb.cf.restaurants.service;

import gr.aueb.cf.restaurants.dto.ReservationInsertDTO;
import gr.aueb.cf.restaurants.model.Reservation;
import gr.aueb.cf.restaurants.model.Restaurant;
import gr.aueb.cf.restaurants.model.Status;
import gr.aueb.cf.restaurants.model.User;
import gr.aueb.cf.restaurants.repository.ReservationRepository;
import gr.aueb.cf.restaurants.repository.RestaurantRepository;
import gr.aueb.cf.restaurants.repository.UserRepository;
import gr.aueb.cf.restaurants.service.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * This class implements the {@link IReservationService} interface.
 * It implements the Public API of the Service Layer that is used from
 * the controllers.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final ReservationRepository reservationRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    /**
     * This method inserts a new {@link Reservation} entity in the database.
     *
     * @param dto the Data Transfer Object that includes the data that the use has inserted.
     * @return the inserted {@link Reservation} entity.
     * @throws Exception A general exception is thrown if an error is occurred.
     */
    @Transactional
    @Override
    public Reservation insertReservation(ReservationInsertDTO dto) throws Exception {
        Reservation reservation = null;
        try {
            reservation = reservationRepository.save(convertTo(dto));
            if (reservation.getReservationId() == null) {
                throw new Exception("[Error]: Inserting new reservation");
            }
        } catch (Exception e) {
            log.info("[Error]: Inserting new reservation\n" + e.getMessage());
            throw e;
        }

        return reservation;
    }

    /**
     * This method finds the reservations of a user given the username. It outputs both
     * old and new reservations.
     *
     * @param username  the username of the user.
     * @return  a List of {@link Reservation} objects with the reservations of the user.
     * @throws EntityNotFoundException This exception is thrown if they are no reservations in the list.
     */
    @Override
    public List<Reservation> getReservationsByUsername(String username) throws EntityNotFoundException {
        List<Reservation> reservations;

        try {
            reservations = reservationRepository.getReservationsByUser(username);
            if (reservations.size() == 0) {
                throw new EntityNotFoundException(Reservation.class, 0L);
            }
        } catch (EntityNotFoundException e) {
            log.info("Reservations empty");
            throw e;
        }
        return reservations;
    }

    /**
     * This private method convert the {@link ReservationInsertDTO} Data
     * Transfer Object to {@link Reservation} Entity in order to insert a new entity.
     * @param dto the Data Transfer Object for a new reservation.
     * @return  a {@link Reservation} object.
     */
    private Reservation convertTo(ReservationInsertDTO dto) {
        Reservation reservation = new Reservation();
        reservation.setReservationId(null);
        reservation.setReservationDate(dto.getReservationDate());
        reservation.setPartySize(dto.getPartySize());
        // setting a reservation status PENDING for new reservations
        reservation.setStatus(Status.PENDING);
        // retrieving restaurant
        Restaurant restaurant = restaurantRepository.getRestaurantByRestaurantId(Long.parseLong(dto.getRestaurantId()));
        reservation.setRestaurant(restaurant);
        // Retrieving User
        Optional<User> user = userRepository.getUserByUsername(dto.getUsername());
        user.ifPresent(reservation::setUser);
        return reservation;
    }
}
