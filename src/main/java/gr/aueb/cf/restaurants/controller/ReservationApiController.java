package gr.aueb.cf.restaurants.controller;


import gr.aueb.cf.restaurants.dto.ReservationInsertDTO;
import gr.aueb.cf.restaurants.dto.ReservationReadOnlyDTO;
import gr.aueb.cf.restaurants.model.Reservation;
import gr.aueb.cf.restaurants.service.IReservationService;
import gr.aueb.cf.restaurants.service.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReservationApiController {

    private final IReservationService reservationService;

    @GetMapping(path = "/reservations")
    public ResponseEntity<List<ReservationReadOnlyDTO>> getReservations() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        try {
            List<Reservation> reservations = reservationService.getReservationsByUsername(username);
            List<ReservationReadOnlyDTO> reservationsReadOnlyDTOs = new ArrayList<>();
            for (Reservation reservation : reservations) {
                reservationsReadOnlyDTOs.add(convertToReadOnly(reservation));
            }
            return new ResponseEntity<>(reservationsReadOnlyDTOs, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/restaurants/{id}/reservation")
    public ResponseEntity<ReservationReadOnlyDTO> insertReservation(
            @PathVariable("id") Long id,
            @RequestBody ReservationInsertDTO dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        dto.setUsername(username);

        try {
            Reservation reservation = reservationService.insertReservation(dto);
            ReservationReadOnlyDTO reservationReadOnlyDTO = convertToReadOnly(reservation);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/reservations")
                    .buildAndExpand()
                    .toUri();

            return ResponseEntity.created(location).body(reservationReadOnlyDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ReservationReadOnlyDTO convertToReadOnly(Reservation reservation) {
        // get date
        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(reservation.getReservationDate());

        return new ReservationReadOnlyDTO(
                reservation.getRestaurant().getName(),
                date,
                reservation.getStatus().name(),
                reservation.getPartySize());
    }
}
