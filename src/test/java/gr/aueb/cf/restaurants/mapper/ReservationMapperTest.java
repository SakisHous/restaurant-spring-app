package gr.aueb.cf.restaurants.mapper;

import gr.aueb.cf.restaurants.dto.ReservationResponseDTO;
import gr.aueb.cf.restaurants.model.Reservation;
import gr.aueb.cf.restaurants.model.Role;
import gr.aueb.cf.restaurants.model.Status;
import gr.aueb.cf.restaurants.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ReservationMapperTest {

    @Autowired
    private ReservationMapper reservationMapper;

    @Test
    void shouldMapToReservationResponseTest() {

        final User user = new User(1L, "testUSer", "12345", "Test Firstname", "Test Lastname", "example@gmail.com", "123456789", Role.USER, List.of(), List.of());

        LocalDate localDate = LocalDate.of(2025, 4, 5);

        Reservation reservation = Reservation.builder()
                                             .reservationId(1L)
                                             .reservationDate(localDate)
                                             .status(Status.PENDING)
                                             .user(user)
                                             .partySize(2)
                                             .build();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String formattedString = localDate.format(formatter);

        ReservationResponseDTO reservationResponse = reservationMapper.toReservationResponse(reservation);
        assertEquals(formattedString, reservationResponse.getReservationDate());
        assertThat(reservationResponse).isNotNull();
    }
}