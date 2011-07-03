import models.products.Tea;
import org.junit.Test;
import play.mvc.Http;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
public class TeasTest extends BaseFunctionalTest {

    @Test
    public void adminList() {
        Http.Response response = GET("/administration/articles/thes/liste");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }

    @Test
    public void adminSave() {

        Tea entity = new Tea(Tea.find("code = ?", "TEST").<Tea>first());
        entity.code = "TEST2";

        Map<String, String> parameters = new HashMap<String, String>();
        // parameters.putAll(entity.);

        // post product
        Http.Response response = POST("/administration/articles/thes/save", parameters);
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);

        // post null
        response = POST("/administration/articles/thes/save");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }
}
