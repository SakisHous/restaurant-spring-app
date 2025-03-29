package gr.aueb.cf.restaurants.controller;

import gr.aueb.cf.restaurants.dto.ReviewInsertDTO;
import gr.aueb.cf.restaurants.dto.ReviewResponseDTO;
import gr.aueb.cf.restaurants.model.Review;
import gr.aueb.cf.restaurants.service.IReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "3. Review API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewApiController {

    private final IReviewService reviewService;

    @Operation(summary = "Fetches all reviews for a specific restaurant")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Reviews successfully retrieved",
                content = {
                    @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                            type = "object",
                            ref = "#/components/schemas/ReviewResponseDTO"))
                }),
            @ApiResponse(responseCode = "401", description = "Unauthorized user"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/restaurants/{id}/review")
    public ResponseEntity<ReviewResponseDTO> insertReview(@PathVariable("id") Long id,
                                                          @RequestBody ReviewInsertDTO dto) {

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
            ReviewResponseDTO reviewResponseDTO = convertToReadOnly(review);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(reviewResponseDTO.getReviewId())
                    .toUri();
            return ResponseEntity.created(location).body(reviewResponseDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ReviewResponseDTO convertToReadOnly(Review review) {
        ReviewResponseDTO dto = new ReviewResponseDTO();
        dto.setReviewId(review.getReviewId());
        dto.setComment(review.getComment());
        dto.setRestaurantId(review.getRestaurant().getRestaurantId());
        dto.setUsername(review.getUser().getUsername());

        return dto;
    }
}
