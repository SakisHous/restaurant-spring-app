package gr.aueb.cf.restaurants.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "\"USER\"")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "USERNAME", length = 150, nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD", length = 150, nullable = false)
    private String password;

    @Column(name = "FIRSTNAME", length = 150, nullable = false)
    private String firstname;

    @Column(name = "LASTNAME", length = 150, nullable = false)
    private String lastname;

    @Column(name = "EMAIL", length = 150,nullable = false, unique = true)
    private String email;

    @Column(name = "PHONE_NUMBER", length = 10, nullable = false, unique = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User() {
    }

    public User(String username, String password, String firstname, String lastname, String email, String phoneNumber, Role role) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private String username;
        private String password;
        private String firstname;
        private String lastname;
        private String email;
        private String phoneNumber;
        private Role role;

        public UserBuilder setUsername(final String username) {
            this.username = username;
            return this;
        }

        public UserBuilder setPassword(final String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setFirstname(final String firstname) {
            this.firstname = firstname;
            return this;
        }
        public UserBuilder setLastname(final String lastname) {
            this.lastname = lastname;
            return this;
        }

        public UserBuilder setEmail(final String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setPhoneNumber(final String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserBuilder setRole(final Role role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(username, password, firstname, lastname, email, phoneNumber, role);
        }
    }

}
