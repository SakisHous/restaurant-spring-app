package gr.aueb.cf.restaurants.service;


/**
 * Interface that defines the Public API of {@link gr.aueb.cf.restaurants.model.User}
 * Entity. It defines the methods that are implemented in the
 * Service Layer.
 */
public interface IUserService {
    boolean isUsernameTaken(String username);
}
