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
public class AuthenticationResponse {

    @Schema(description="The JWT Token", example = "QWE#FDKF@#@!DMF2SF&MD1KL")
    private String token;
}
