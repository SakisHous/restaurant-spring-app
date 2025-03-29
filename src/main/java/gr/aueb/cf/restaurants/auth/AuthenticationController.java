package gr.aueb.cf.restaurants.auth;

import gr.aueb.cf.restaurants.dto.RegisterUserDTO;
import gr.aueb.cf.restaurants.validator.UserRegisterValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "2. Authentication API")
@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserRegisterValidator userRegisterValidator;

    @Operation(summary = "Register a new customer")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "201",
                description = "User successfully registered",
                content = {
                    @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                            type = "object",
                            ref = "#/components/schemas/AuthenticationResponse"))
                }),
            @ApiResponse(responseCode = "500", description = "Internal Service Error")
    })
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterUserDTO dto,
                                                           BindingResult bindingResult) {

        userRegisterValidator.validate(dto, bindingResult);

        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(authenticationService.register(dto));
    }

    @Operation(summary = "Authenticate customer")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "User successfully authenticated",
                content = {
                    @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                            type = "object",
                            ref = "#/components/schemas/AuthenticationResponse"))
                }),
            @ApiResponse(responseCode = "500", description = "Internal Service Error")
    })
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {

        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
