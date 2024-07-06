package gr.aueb.cf.restaurants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "RESTAURANT")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESTAURANT_ID")
    private Long restaurantId;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "ADDRESS", length = 50, nullable = false)
    private String address;

    @Column(name = "CONTACT_NUMBER", length = 10, nullable = false, unique = true)
    private String contactNumber;

    @Column(name = "CITY", length = 100, nullable = false)
    private String cityName;

    private String openHour;

    private String closeHour;

    private String menuDescription;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "restaurant")
    @JsonIgnore
    private List<Review> reviews;
}
