package gr.aueb.cf.restaurants.controller;

import gr.aueb.cf.restaurants.dto.RestaurantInsertDTO;
import gr.aueb.cf.restaurants.dto.RestaurantResponseDTO;
import gr.aueb.cf.restaurants.dto.RestaurantUpdateDTO;
import gr.aueb.cf.restaurants.mapper.ReservationMapper;
import gr.aueb.cf.restaurants.mapper.RestaurantMapper;
import gr.aueb.cf.restaurants.model.Restaurant;
import gr.aueb.cf.restaurants.service.IRestaurantService;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Tag(name = "1. Restaurant API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RestaurantApiController {

    private final IRestaurantService restaurantService;
    private final RestaurantMapper restaurantResponseMapper;
    private final ReservationMapper reservationMapper;

    @Operation(summary = "Fetches restaurant by id")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Restaurant successfully found",
                content = {
                    @Content(
                        mediaType = "application/json",
                        schema =  @Schema(
                            type = "object",
                            ref = "#/components/schemas/RestaurantResponseDTO"))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized user"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/restaurants/{id}")
    public ResponseEntity<RestaurantResponseDTO> getRestaurant(@PathVariable("id") Long restaurantId) {

        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);

        if (restaurant == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        RestaurantResponseDTO restaurantResponseDto = restaurantResponseMapper.toResponseDto(restaurant);
        return new ResponseEntity<>(restaurantResponseDto, HttpStatus.OK);
    }

    @Operation(summary = "Fetches all the restaurants")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Restaurants successfully found",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(
                                type = "object",
                                ref = "#/components/schemas/RestaurantResponseDTO")))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized user"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
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

    @Operation(summary = "Inserts new restaurant")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "201",
                description = "Restaurant successfully created",
                content = {
                    @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                            type = "object",
                            ref = "#/components/schemas/RestaurantResponseDTO"))
                }),
            @ApiResponse(responseCode = "401", description = "Unauthorized user"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/restaurants")
    public ResponseEntity<RestaurantResponseDTO> addRestaurant(@RequestBody RestaurantInsertDTO restaurantInsertDTO,
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

    @Operation(summary = "Update restaurant info")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Restaurant successfully updated",
                content =
                    @Content(
                        mediaType = "application/json",
                        schema =  @Schema(
                            type = "object",
                            ref = "#/components/schemas/RestaurantResponseDTO"))),
            @ApiResponse(responseCode = "401", description = "Unauthorized user"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping("restaurants/{id}")
    public ResponseEntity<RestaurantResponseDTO> updateRestaurant(@PathVariable("id") Long id,
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

    @Operation(summary = "Delete restaurant")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Restaurant successfully deleted",
                content =
                @Content(
                    mediaType = "application/json",
                    schema =  @Schema(
                        type = "object",
                        ref = "#/components/schemas/RestaurantResponseDTO"))),
            @ApiResponse(responseCode = "401", description = "Unauthenticated user"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
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
