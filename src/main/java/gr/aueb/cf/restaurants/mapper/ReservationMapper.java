package gr.aueb.cf.restaurants.mapper;

import gr.aueb.cf.restaurants.dto.ReservationResponseDTO;
import gr.aueb.cf.restaurants.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReservationMapper {

    @Mapping(source = "restaurant.name", target = "restaurantName")
    @Mapping(source = "reservation.reservationDate", target = "reservationDate", dateFormat = "MM-dd-yyyy")
    ReservationResponseDTO toReservationResponse(Reservation reservation);
}
