package models.users;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
@Entity
public class SocialType extends Model {

    public static final String MME = "mme";
    public static final String MLLE = "mlle";
    public static final String MR = "mr";

    public SocialType(String name) {
        this.name = name;
    }

    @Required
    public String name;
}
