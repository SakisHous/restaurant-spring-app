package gr.aueb.cf.restaurants.repository;

import gr.aueb.cf.restaurants.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface defines the repository for the {@link User} entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.username = ?1 AND u.password = ?2")
    boolean isUserValid(String username, String password);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.username = ?1")
    boolean usernameExists(String username);

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    Optional<User> getUserByUsername(String username);
}
