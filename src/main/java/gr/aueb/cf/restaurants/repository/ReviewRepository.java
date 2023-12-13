package gr.aueb.cf.restaurants.repository;

import gr.aueb.cf.restaurants.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface defines the repository for the {@link Review} entity.
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
