package controllers;

import models.products.Product;

import javax.persistence.ColumnResult;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
@CRUD.For(value = Product.class)
public class Products extends CRUD {


}
