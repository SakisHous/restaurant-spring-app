package gr.aueb.cf.restaurants.dto;

public class RestaurantReadOnlyDTO {
    private Long restaurantId;
    private String name;
    private String address;
    private String contactNumber;
    private String city;
    private String menuDescription;
    private String openHour;
    private String closeHour;

    public RestaurantReadOnlyDTO() {
    }

    public RestaurantReadOnlyDTO(Long restaurantId, String name, String address, String contactNumber, String city, String menuDescription, String openHour, String closeHour) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.city = city;
        this.menuDescription = menuDescription;
        this.openHour = openHour;
        this.closeHour = closeHour;
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

    public String getOpenHour() {
        return openHour;
    }

    public void setOpenHour(String openHour) {
        this.openHour = openHour;
    }

    public String getCloseHour() {
        return closeHour;
    }

    public void setCloseHour(String closeHour) {
        this.closeHour = closeHour;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }
}
