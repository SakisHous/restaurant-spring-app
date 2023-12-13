package gr.aueb.cf.restaurants.controller;

import gr.aueb.cf.restaurants.dto.RestaurantInsertDTO;
import gr.aueb.cf.restaurants.dto.RestaurantReadOnlyDTO;
import gr.aueb.cf.restaurants.dto.RestaurantUpdateDTO;
import gr.aueb.cf.restaurants.model.Restaurant;
import gr.aueb.cf.restaurants.service.IRestaurantService;
import gr.aueb.cf.restaurants.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class RestaurantApiController {

    private final IRestaurantService restaurantService;

    @Autowired
    public RestaurantApiController(IRestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/restaurants/{id}")
    public ResponseEntity<RestaurantReadOnlyDTO> getRestaurant(@PathVariable("id") Long restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);

        if (restaurant == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        RestaurantReadOnlyDTO restaurantReadOnlyDto = convertToReadOnlyDto(restaurant);
        return new ResponseEntity<>(restaurantReadOnlyDto, HttpStatus.OK);
    }

    @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantReadOnlyDTO>> getRestaurantsInCity(@PathParam("city") String city) {
        List<Restaurant> restaurants = restaurantService.getRestaurantByCity(city);

        if (restaurants.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<RestaurantReadOnlyDTO> restaurantsReadOnlyDto = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            restaurantsReadOnlyDto.add(convertToReadOnlyDto(restaurant));
        }
        return new ResponseEntity<>(restaurantsReadOnlyDto, HttpStatus.OK);
    }

    @PostMapping("/restaurants")
    public ResponseEntity<RestaurantReadOnlyDTO> addRestaurant(
            @RequestBody RestaurantInsertDTO restaurantInsertDTO,
            BindingResult bindingResult) {

        try {
            Restaurant restaurant = restaurantService.insertRestaurant(restaurantInsertDTO);
            RestaurantReadOnlyDTO restaurantReadOnlyDto = convertToReadOnlyDto(restaurant);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(restaurantReadOnlyDto.getRestaurantId())
                    .toUri();
            return ResponseEntity.created(location).body(restaurantReadOnlyDto);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("restaurants/{id}")
    public ResponseEntity<RestaurantReadOnlyDTO> updateRestaurant(
            @PathVariable("id") Long id,
            @RequestBody RestaurantUpdateDTO updateDTO,
            BindingResult bindingResult) {

        if (!Objects.equals(updateDTO.getRestaurantId(), id)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            Restaurant restaurant = restaurantService.updateRestaurant(updateDTO);
            RestaurantReadOnlyDTO dto = convertToReadOnlyDto(restaurant);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/restaurants/{id}")
    public ResponseEntity<RestaurantReadOnlyDTO> deleteRestaurant(@PathVariable("id") Long id) {
        Restaurant restaurant;

        try {
            restaurant = restaurantService.deleteRestaurantById(id);
            RestaurantReadOnlyDTO dto = convertToReadOnlyDto(restaurant);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private RestaurantReadOnlyDTO convertToReadOnlyDto(Restaurant restaurant) {
        return new RestaurantReadOnlyDTO(
                restaurant.getRestaurantId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getContactNumber(),
                restaurant.getCityName(),
                restaurant.getMenuDescription(),
                restaurant.getOpenHour(),
                restaurant.getCloseHour()
        );
    }
}
