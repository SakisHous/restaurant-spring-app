package gr.aueb.cf.restaurants.mapper;

import gr.aueb.cf.restaurants.dto.RestaurantResponseDTO;
import gr.aueb.cf.restaurants.model.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RestaurantMapperTest {

    @Autowired
    private RestaurantMapper restaurantMapper;

    @Test
    void shouldMapTorRestaurantResponseTest() {
        Restaurant restaurant = Restaurant.builder()
                .restaurantId(1L)
                .address("5th Avenue, NY")
                .name("Diner's")
                .cityName("New York")
                .contactNumber("15484848")
                .openHour("18:00")
                .closeHour("00:00")
                .menuDescription("Menu Description")
                .build();

        RestaurantResponseDTO restaurantResponseDTO = restaurantMapper.toResponseDto(restaurant);

        assertThat(restaurantResponseDTO).isNotNull();
    }
}