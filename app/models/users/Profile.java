package models.users;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
@Entity
public class Profile extends Model {

    public static final String CLIENT_STANDARD = "client";
    public static final String CLIENT_LOYAL = "loyal";
    public static final String ADMIN = "admin";

    @Required
    public String name;

    @Override
    public String toString() {
        return "Profile{name='" + name + "'}";
    }
}
