package gr.aueb.cf.restaurants.controller;


import gr.aueb.cf.restaurants.dto.ReservationInsertDTO;
import gr.aueb.cf.restaurants.dto.ReservationResponseDTO;
import gr.aueb.cf.restaurants.mapper.ReservationMapper;
import gr.aueb.cf.restaurants.model.Reservation;
import gr.aueb.cf.restaurants.service.IReservationService;
import gr.aueb.cf.restaurants.service.exceptions.EntityNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "4. Reservation API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReservationApiController {

    private final IReservationService reservationService;
    private final ReservationMapper reservationMapper;

    @Operation(summary = "Fetches all reservations for a customer.")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Reservations successfully retrieved",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array =  @ArraySchema(
                            schema = @Schema(
                                type = "object",
                                ref = "#/components/schemas/ReservationResponseDTO"))
                    )
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized user"),
            @ApiResponse(responseCode = "404", description = "Reservations not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping(path = "/reservations")
    public ResponseEntity<List<ReservationResponseDTO>> getReservations() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        try {
            List<Reservation> reservations = reservationService.getReservationsByUsername(username);
            List<ReservationResponseDTO> reservationsReadOnlyDTOs = new ArrayList<>();
            for (Reservation reservation : reservations) {
                reservationsReadOnlyDTOs.add(reservationMapper.toReservationResponse(reservation));
            }
            return new ResponseEntity<>(reservationsReadOnlyDTOs, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Creates a reservation for a restaurant with this Id.")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "201",
                description = "Reservation successfully created.",
                content = {
                    @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                                type = "object",
                                ref = "#/components/schemas/ReservationResponseDTO"))
                }),
            @ApiResponse(responseCode = "401", description = "Unauthorized user"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping(path = "/restaurants/{id}/reservation")
    public ResponseEntity<ReservationResponseDTO> insertReservation(@PathVariable("id") Long id,
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
            ReservationResponseDTO reservationResponseDTO = reservationMapper.toReservationResponse(reservation);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/reservations")
                    .buildAndExpand()
                    .toUri();

            return ResponseEntity.created(location).body(reservationResponseDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ReservationResponseDTO convertToReadOnly(Reservation reservation) {
        // get date
        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(reservation.getReservationDate());

        return new ReservationResponseDTO(
                reservation.getRestaurant().getName(),
                date,
                reservation.getStatus().name(),
                reservation.getPartySize());
    }
}
