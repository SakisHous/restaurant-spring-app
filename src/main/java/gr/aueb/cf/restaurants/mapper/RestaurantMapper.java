package gr.aueb.cf.restaurants.mapper;

import gr.aueb.cf.restaurants.dto.RestaurantResponseDTO;
import gr.aueb.cf.restaurants.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RestaurantMapper {

    RestaurantResponseDTO toResponseDto(Restaurant restaurant);
}
