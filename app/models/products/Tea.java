package models.products;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
@Entity
@DiscriminatorValue(value = "TEA")
public class Tea extends Product {

}
