package models.products;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
@Entity
@Table(name = "t_family")
public class Family extends Model {

    @Required
    public String label;

    @Required
    public String value;

    @Required
    @Column(name = "family_order")
    public Integer order;

    @Required
    public String productType;

    @Required
    public String productTypeLabel;
}
