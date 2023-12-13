package gr.aueb.cf.restaurants.dto;

public class RestaurantUpdateDTO {
    private Long restaurantId;
    private String name;
    private String address;
    private String contactNumber;
    private String city;

    public RestaurantUpdateDTO() {
    }

    public RestaurantUpdateDTO(Long restaurantId, String name, String address, String contactNumber, String city) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.city = city;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
