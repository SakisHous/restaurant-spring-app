package gr.aueb.cf.restaurants.service;

import gr.aueb.cf.restaurants.dto.ReviewInsertDTO;
import gr.aueb.cf.restaurants.model.Restaurant;
import gr.aueb.cf.restaurants.model.Review;
import gr.aueb.cf.restaurants.model.User;
import gr.aueb.cf.restaurants.repository.RestaurantRepository;
import gr.aueb.cf.restaurants.repository.ReviewRepository;
import gr.aueb.cf.restaurants.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * This class implements the {@link IReviewService} interface
 * that defines the Public API for the {@link Review} entity.
 */
@Service
@Slf4j
public class ReviewServiceImpl implements IReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * This method inserts a new {@link Review} entity in the database.
     *
     * @param dto the Data Transfer Object for the insertion of a new entity.
     * @return  the inserted {@link Review} entity.
     * @throws Exception a general exception if an error is occurred during the
     * insertion of the entity.
     */
    @Transactional
    @Override
    public Review insertReview(ReviewInsertDTO dto) throws Exception {
        Review review;

        try {
            review = reviewRepository.save(mapFrom(dto));
            if (review.getReviewId() == null) {
                throw new Exception("[Error]: Inserting Review");
            }
        } catch (Exception e) {
            log.info("[Error]: Inserting Review\n" + e.getMessage());
            throw e;
        }
        return review;
    }

    /**
     * This method converts a {@link ReviewInsertDTO} Data Transfer Object to
     * {@link Review} entity.
     *
     * @param dto the Data Transfer Object for the insertion of new review.
     * @return the {@link Review} entity to be inserted.
     */
    private Review mapFrom(ReviewInsertDTO dto) {
        Review review = new Review();
        review.setComment(dto.getComment());
        // Retrieving User
        Optional<User> user = userRepository.getUserByUsername(dto.getUsername());
        user.ifPresent(review::setUser);
        // Retrieving Restaurant
        Restaurant restaurant = restaurantRepository.getRestaurantByRestaurantId(dto.getRestaurantId());
        review.setRestaurant(restaurant);
        return review;
    }
}
