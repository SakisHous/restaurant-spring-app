package gr.aueb.cf.restaurants.service;

import gr.aueb.cf.restaurants.dto.ReviewInsertDTO;
import gr.aueb.cf.restaurants.model.Review;


/**
 * Interface that defines the Public API of {@link Review}
 * Entity. It defines the methods that are implemented in the
 * Service Layer.
 */
public interface IReviewService {
    Review insertReview(ReviewInsertDTO dto) throws Exception;
}
