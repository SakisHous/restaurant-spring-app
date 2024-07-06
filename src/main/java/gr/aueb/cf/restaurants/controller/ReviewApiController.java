package gr.aueb.cf.restaurants.controller;

import gr.aueb.cf.restaurants.dto.ReviewInsertDTO;
import gr.aueb.cf.restaurants.dto.ReviewReadOnlyDTO;
import gr.aueb.cf.restaurants.model.Review;
import gr.aueb.cf.restaurants.service.IReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewApiController {

    private final IReviewService reviewService;

    @PostMapping("/restaurants/{id}/review")
    public ResponseEntity<ReviewReadOnlyDTO> insertReview(@PathVariable("id") Long id, @RequestBody ReviewInsertDTO dto) {
        if (!Objects.equals(id, dto.getRestaurantId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        dto.setUsername(userDetails.getUsername());

        Review review;
        try {
            review = reviewService.insertReview(dto);
            ReviewReadOnlyDTO reviewReadOnlyDTO = convertToReadOnly(review);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(reviewReadOnlyDTO.getReviewId())
                    .toUri();
            return ResponseEntity.created(location).body(reviewReadOnlyDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ReviewReadOnlyDTO convertToReadOnly(Review review) {
        ReviewReadOnlyDTO dto = new ReviewReadOnlyDTO();
        dto.setReviewId(review.getReviewId());
        dto.setComment(review.getComment());
        dto.setRestaurantId(review.getRestaurant().getRestaurantId());
        dto.setUsername(review.getUser().getUsername());

        return dto;
    }
}
