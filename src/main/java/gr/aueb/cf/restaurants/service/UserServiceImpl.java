package gr.aueb.cf.restaurants.service;

import gr.aueb.cf.restaurants.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class implements the {@link IUserService} interface which
 * defines the Public API of the {@link gr.aueb.cf.restaurants.model.User} entity.
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
