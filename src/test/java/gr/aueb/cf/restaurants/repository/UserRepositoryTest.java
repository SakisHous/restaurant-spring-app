package gr.aueb.cf.restaurants.repository;

import gr.aueb.cf.restaurants.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User mockUser = new User();
        mockUser.setUsername("testUsername");
        mockUser.setPassword("12345");
        mockUser.setFirstname("testFirstname");
        mockUser.setLastname("testLastname");
        mockUser.setEmail("test@email.com");
        mockUser.setPhoneNumber("1201234567");

        userRepository.save(mockUser);
        userRepository.flush();
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        userRepository.flush();
    }

    @Test
    void usernameExists() {
        String username = "testUsername";

        Boolean userExists = userRepository.usernameExists(username);

        assertThat(userExists).isTrue();
    }

    @Test
    void getUserByUsername() {
        String username = "testUsername";

        Optional<User> user = userRepository.getUserByUsername(username);

        assertThat(user).isNotNull();
    }
}