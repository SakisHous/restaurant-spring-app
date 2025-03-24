package gr.aueb.cf.restaurants.controller;

import gr.aueb.cf.restaurants.dto.RestaurantInsertDTO;
import gr.aueb.cf.restaurants.dto.RestaurantResponseDTO;
import gr.aueb.cf.restaurants.dto.RestaurantUpdateDTO;
import gr.aueb.cf.restaurants.mapper.ReservationMapper;
import gr.aueb.cf.restaurants.mapper.RestaurantMapper;
import gr.aueb.cf.restaurants.model.Restaurant;
import gr.aueb.cf.restaurants.service.IRestaurantService;
import gr.aueb.cf.restaurants.service.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class RestaurantApiController {

    private final IRestaurantService restaurantService;
    private final RestaurantMapper restaurantResponseMapper;
    private final ReservationMapper reservationMapper;

    @GetMapping("/restaurants/{id}")
    public ResponseEntity<RestaurantResponseDTO> getRestaurant(@PathVariable("id") Long restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);

        if (restaurant == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        RestaurantResponseDTO restaurantResponseDto = restaurantResponseMapper.toResponseDto(restaurant);
        return new ResponseEntity<>(restaurantResponseDto, HttpStatus.OK);
    }

    @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantResponseDTO>> getRestaurantsInCity(@PathParam("city") String city) {
        List<Restaurant> restaurants = restaurantService.getRestaurantByCity(city);

        if (restaurants.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<RestaurantResponseDTO> restaurantsReadOnlyDto = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            restaurantsReadOnlyDto.add(restaurantResponseMapper.toResponseDto(restaurant));
        }
        return new ResponseEntity<>(restaurantsReadOnlyDto, HttpStatus.OK);
    }

    @PostMapping("/restaurants")
    public ResponseEntity<RestaurantResponseDTO> addRestaurant(
            @RequestBody RestaurantInsertDTO restaurantInsertDTO,
            BindingResult bindingResult) {

        try {
            Restaurant restaurant = restaurantService.insertRestaurant(restaurantInsertDTO);
            RestaurantResponseDTO restaurantResponseDto = restaurantResponseMapper.toResponseDto(restaurant);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(restaurantResponseDto.getRestaurantId())
                    .toUri();
            return ResponseEntity.created(location).body(restaurantResponseDto);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("restaurants/{id}")
    public ResponseEntity<RestaurantResponseDTO> updateRestaurant(
            @PathVariable("id") Long id,
            @RequestBody RestaurantUpdateDTO updateDTO,
            BindingResult bindingResult) {

        if (!Objects.equals(updateDTO.getRestaurantId(), id)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            Restaurant restaurant = restaurantService.updateRestaurant(updateDTO);
            RestaurantResponseDTO dto = restaurantResponseMapper.toResponseDto(restaurant);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/restaurants/{id}")
    public ResponseEntity<RestaurantResponseDTO> deleteRestaurant(@PathVariable("id") Long id) {
        Restaurant restaurant;

        try {
            restaurant = restaurantService.deleteRestaurantById(id);
            RestaurantResponseDTO dto = restaurantResponseMapper.toResponseDto(restaurant);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
