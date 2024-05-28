package gr.aueb.cf.restaurants.controller;

import gr.aueb.cf.restaurants.model.Restaurant;
import gr.aueb.cf.restaurants.service.IRestaurantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestaurantApiController.class)
class RestaurantApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    IRestaurantService restaurantService;

    @Test
    void getRestaurant() throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(1L);
        given(restaurantService.getRestaurantById(any())).willReturn(restaurant);

        mockMvc.perform(get("/api/restaurants/{id}", restaurant.getRestaurantId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(restaurant.getRestaurantId())));
    }

    @Test
    void getRestaurantsInCity() {
    }

    @Test
    void addRestaurant() {
    }

    @Test
    void updateRestaurant() {
    }

    @Test
    void deleteRestaurant() {
    }
}