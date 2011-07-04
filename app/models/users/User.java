package models.users;


import org.hibernate.validator.constraints.Email;
import org.joda.time.LocalDateTime;
import play.db.jpa.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"email"})
        }
)
public class User extends Model {

    public String clientIdentifier;

    @NotNull
    public LocalDateTime registerDate;

    public LocalDateTime lastLoginDate;

    @Email
    @NotNull
    public String email;

    @NotNull
    public String password;

    @NotNull
    public String firstName;

    @NotNull
    public String lastName;

    @NotNull
    public String street;

    public String streetComplement;

    @NotNull
    public String city;

    @NotNull
    public String postalCode;

    @NotNull
    public String country;

    public String fax;

    public String phone;

    @NotNull
    public Boolean newsletter = false;

    @NotNull
    public Boolean banned = false;

    @NotNull
    @ManyToOne
    public SocialType socialType;

    @NotNull
    @ManyToOne
    public Profile profile;

    @PostPersist
    public void generateClientIdentifier() {
        clientIdentifier = "CI" + (id * 100) + registerDate.monthOfYear().get() + registerDate.year().get();
    }
}
