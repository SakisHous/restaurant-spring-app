package gr.aueb.cf.restaurants.service;

import gr.aueb.cf.restaurants.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * This class implements the {@link IUserService} interface which
 * defines the Public API of the {@link gr.aueb.cf.restaurants.model.User} entity.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    /**
     * This method checks if a username is already exists in the database.
     *
     * @param username the username to search for.
     * @return true if the username exists, otherwise false.
     */
    @Override
    public boolean isUsernameTaken(String username) {
        return userRepository.usernameExists(username);
    }
}
