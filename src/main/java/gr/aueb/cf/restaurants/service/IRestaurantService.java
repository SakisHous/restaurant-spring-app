package gr.aueb.cf.restaurants.service;

import gr.aueb.cf.restaurants.dto.RestaurantInsertDTO;
import gr.aueb.cf.restaurants.dto.RestaurantUpdateDTO;
import gr.aueb.cf.restaurants.model.Restaurant;
import gr.aueb.cf.restaurants.service.exceptions.EntityNotFoundException;

import java.util.List;

/**
 * Interface that defines the Public API for the {@link Restaurant}
 * Entity. It defines the methods that are implemented in the
 *  * Service Layer.
 */
public interface IRestaurantService {
    Restaurant insertRestaurant(RestaurantInsertDTO dto) throws Exception;
    Restaurant updateRestaurant(RestaurantUpdateDTO dto) throws EntityNotFoundException;
    Restaurant deleteRestaurantById(Long id) throws EntityNotFoundException;
    Restaurant getRestaurantById(Long id);
    List<Restaurant> getRestaurantByCity(String city);
}
