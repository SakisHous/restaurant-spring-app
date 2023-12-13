package gr.aueb.cf.restaurants.dto;

public class RestaurantInsertDTO {
    private String name;
    private String address;
    private String contactNumber;
    private String city;

    public RestaurantInsertDTO() {
    }

    public RestaurantInsertDTO(String name, String address, String contactNumber, String city) {
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.city = city;
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
