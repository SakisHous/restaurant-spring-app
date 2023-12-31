package gr.aueb.cf.restaurants.auth;

import gr.aueb.cf.restaurants.dto.RegisterUserDTO;
import gr.aueb.cf.restaurants.validator.UserRegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserRegisterValidator userRegisterValidator;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, UserRegisterValidator userRegisterValidator) {
        this.authenticationService = authenticationService;
        this.userRegisterValidator = userRegisterValidator;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterUserDTO dto,
            BindingResult bindingResult) {
        userRegisterValidator.validate(dto, bindingResult);
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(authenticationService.register(dto));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
