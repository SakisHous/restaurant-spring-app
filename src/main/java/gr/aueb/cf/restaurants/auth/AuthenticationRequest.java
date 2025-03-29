package gr.aueb.cf.restaurants.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {

    @Schema(description="The username of the customer.", example = "username")
    String username;
    @Schema(description="The password of the customer.", example = "password123")
    String password;
}
