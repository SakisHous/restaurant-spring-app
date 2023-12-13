package gr.aueb.cf.restaurants.service;

import gr.aueb.cf.restaurants.dto.ReservationInsertDTO;
import gr.aueb.cf.restaurants.model.Reservation;
import gr.aueb.cf.restaurants.service.exceptions.EntityNotFoundException;

import java.util.List;

/**
 * Interface that defines the Public API for the {@link Reservation}
 * Entity. It defines the methods that are implemented in the
 * Service Layer.
 */
public interface IReservationService {
    Reservation insertReservation(ReservationInsertDTO dto) throws Exception;
    List<Reservation> getReservationsByUsername(String username) throws EntityNotFoundException;
}
