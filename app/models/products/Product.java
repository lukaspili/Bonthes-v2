package models.products;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.*;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
@Entity
@Table(name = "t_product")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator")
public class Product extends Model {

    @Required
    @Column(name = "pname")
    public String name;

    @Required
    @Column(name = "normalized_name")
    public String ident;

    @Required
    @Column(name = "code")
    public String code;

    @Required
    @Lob
    @Column(name = "description")
    public String description;

    @Required
    @Column(name = "price_ht")
    public Double priceHT;

    @Required
    @Column(name = "stock_web")
    public Integer stock;

    @Required
    @Column(name = "weight_grams")
    public Double weight;

    @Required
    @Column(name = "reduction")
    public Boolean reduction;

    @Required
    @Column(name = "desactivated")
    public Boolean desactivated;

    @Required
    @Column(name = "has_picture")
    public Boolean hasPicture;

    @Required
    @ManyToOne
    public Family family;

    @Required
    @ManyToOne
    public Tva tva;

    public Product() {

    }

    public Product(Product product) {

        name = product.name;
        ident = product.ident;
        code = product.code;
        description = product.description;
        priceHT = product.priceHT;
        stock = product.stock;
        weight = product.weight;
        reduction = product.reduction;
        desactivated = product.desactivated;
        hasPicture = product.hasPicture;
        family = product.family;
        tva = product.tva;
    }
}
