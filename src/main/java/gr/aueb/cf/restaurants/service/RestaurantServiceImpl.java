package gr.aueb.cf.restaurants.service;

import gr.aueb.cf.restaurants.dto.RestaurantInsertDTO;
import gr.aueb.cf.restaurants.dto.RestaurantUpdateDTO;
import gr.aueb.cf.restaurants.model.Restaurant;
import gr.aueb.cf.restaurants.repository.RestaurantRepository;
import gr.aueb.cf.restaurants.service.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the {@link IReservationService} interface.
 * It implements the Public API of the Service Layer that is used from
 * the controllers.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantServiceImpl implements IRestaurantService {

    private final RestaurantRepository restaurantRepository;

    /**
     * This method inserts a new {@link Restaurant} entity in the database.
     *
     * @param dto the Data Transfer Object for a new restaurant to be inserted.
     * @return the inserted {@link Restaurant} entity.
     * @throws Exception A general exception if an error is occurred during insertion.
     */
    @Transactional
    @Override
    public Restaurant insertRestaurant(RestaurantInsertDTO dto) throws Exception {
        Restaurant restaurant;

        try {
            restaurant = restaurantRepository.save(convertFromInsertDto(dto));
            if (restaurant.getRestaurantId() == null) {
                throw new Exception("[Error]: Inserting new restaurant");
            }
        } catch (Exception e) {
            log.info("[Error]: Inserting new restaurant\n" + e.getMessage());
            throw e;
        }
        return restaurant;
    }

    /**
     * This method updates an existing {@link Restaurant} entity in the database.
     *
     * @param dto the Data Transfer Object for the update of the entity.
     * @return the updated {@link Restaurant} entity.
     * @throws EntityNotFoundException this exception is thrown if the entity to be updated is
     *                                 not found.
     */
    @Transactional
    @Override
    public Restaurant updateRestaurant(RestaurantUpdateDTO dto) throws EntityNotFoundException {
        Restaurant restaurant;
        Restaurant updatedRestaurant;

        try {
            restaurant = restaurantRepository.getRestaurantByRestaurantId(dto.getRestaurantId());
            if (restaurant == null) {
                throw new EntityNotFoundException(Restaurant.class, dto.getRestaurantId());
            }
            updatedRestaurant = restaurantRepository.save(convertFromUpdateDto(dto));

        } catch (EntityNotFoundException e) {
            log.info("[Error]: Updating restaurant\n" + e.getMessage());
            throw e;
        }
        return updatedRestaurant;
    }

    /**
     * This method deletes an {@link Restaurant} entity from the database.
     *
     * @param id the id of the entity to be deleted.
     * @return the deleted {@link Restaurant} entity.
     * @throws EntityNotFoundException this exception is thrown if the entity to deleted
     *                                 is not found.
     */
    @Transactional
    @Override
    public Restaurant deleteRestaurantById(Long id) throws EntityNotFoundException {
        Restaurant restaurant;

        try {
            restaurant = restaurantRepository.getRestaurantByRestaurantId(id);
            if (restaurant == null) {
                throw new EntityNotFoundException(Restaurant.class, id);
            }
            restaurantRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            log.info("[Error]: Deleting restaurant with id= " + id + "\n" + e.getMessage());
            throw e;
        }
        return restaurant;
    }

    /**
     * This method returns a {@link Restaurant} entity given the id of it.
     *
     * @param id the id of the entity.
     * @return a {@link Restaurant} object.
     */
    @Override
    public Restaurant getRestaurantById(Long id) {
        Restaurant restaurant = null;
        try {
            restaurant = restaurantRepository.getRestaurantByRestaurantId(id);
        } catch (Exception e) {
            log.info("[Error]: Retrieving restaurant by id\n" + e.getMessage());
        }
        return restaurant;
    }

    /**
     * This method returns a list of {@link Restaurant} entities given the city that
     * they are located.
     *
     * @param city the city where the restaurants are located.
     * @return a list of {@link Restaurant} objects.
     */
    @Override
    public List<Restaurant> getRestaurantByCity(String city) {
        List<Restaurant> restaurants = new ArrayList<>();

        try {
            restaurants = restaurantRepository.getRestaurantByCityName(city);
        } catch (Exception e) {
            log.info("[Error]: Retrieving restaurant by id\n" + e.getMessage());
        }
        return restaurants;
    }

    /**
     * This method maps the {@link RestaurantInsertDTO} Data Transfer Object to
     * {@link Restaurant} entity for the insertion of a new restaurant.
     *
     * @param dto the Data Transfer Object for the insertion of a restaurant.
     * @return a {@link Restaurant} entity to be inserted.
     */
    private Restaurant convertFromInsertDto(RestaurantInsertDTO dto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(null);
        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        restaurant.setContactNumber(dto.getContactNumber());
        restaurant.setCityName(dto.getCity());
        return restaurant;
    }

    /**
     * This method maps the {@link RestaurantUpdateDTO} Data Transfer Object to
     * {@link Restaurant} entity for updating an existing entity.
     *
     * @param dto the Data Transfer Object for the updating of a restaurant.
     * @return the entity to replace the old one.
     */
    private Restaurant convertFromUpdateDto(RestaurantUpdateDTO dto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(dto.getRestaurantId());
        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        restaurant.setContactNumber(dto.getContactNumber());
        restaurant.setCityName(dto.getCity());
        return restaurant;
    }
}
