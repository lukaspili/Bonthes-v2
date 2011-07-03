package controllers;

import models.products.Tea;
import play.Logger;
import play.data.validation.Required;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
public class Teas extends Controller {

    public static void adminList() {

        List<Tea> teas = Tea.findAll();

        render(teas);
    }

    public static void adminSave(@Required Tea tea) {

        if(! validation.errorsMap().isEmpty()) {
            Logger.debug("validation error");
        }

        adminList();
    }
}
