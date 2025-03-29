package gr.aueb.cf.restaurants.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDTO {

    @Schema(description="The username of the customer.", example = "userName")
    private String username;
    @Schema(description="The password of the customer.", example = "password123")
    private String password;
    @Schema(description="The confirmation password of the customer.", example = "password123")
    private String confirmPassword;
    @Schema(description="The firstname of the customer.", example = "Firstname")
    private String firstname;
    @Schema(description="The lastname of the customer.", example = "Lastname")
    private String lastname;
    @Schema(description="The email of the customer.", example = "customer.email@gmail.com")
    private String email;
    @Schema(description="The phone number of the customer.", example = "123456789")
    private String phoneNumber;
}
