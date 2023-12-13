package gr.aueb.cf.restaurants.repository;

import gr.aueb.cf.restaurants.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface defines the repository for the {@link Restaurant} entity.
 */
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Restaurant getRestaurantByRestaurantId(Long id);
    List<Restaurant> getRestaurantByCityName(String city);
}
