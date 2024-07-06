package gr.aueb.cf.restaurants.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantUpdateDTO {
    private Long restaurantId;
    private String name;
    private String address;
    private String contactNumber;
    private String city;
}
