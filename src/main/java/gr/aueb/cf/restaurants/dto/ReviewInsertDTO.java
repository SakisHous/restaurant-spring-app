package gr.aueb.cf.restaurants.dto;

public class ReviewInsertDTO {
    private String comment;
    private String username;
    private Long restaurantId;

    public ReviewInsertDTO() {
    }

    public ReviewInsertDTO(String comment, String username, Long restaurantId) {
        this.comment = comment;
        this.username = username;
        this.restaurantId = restaurantId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
