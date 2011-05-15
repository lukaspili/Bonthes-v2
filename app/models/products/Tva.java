package models.products;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
@Entity
@Table(name = "t_tva")
public class Tva extends Model {

    @Required
    public String label;

    @Required
    public Double value;
}