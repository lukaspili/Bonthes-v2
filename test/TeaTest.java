import controllers.Products;
import models.products.Product;
import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class TeaTest extends BaseUnitTest {

    @Test
    public void crud() {

        // add
        assertEquals(1, Product.count());

        // read
        Product product = Product.find("code = ?", "AAA").first();
        assertEquals("AAA", product.code);

        // update
        product.code = "BBB";
        product.save();

        product = Product.find("code = ?", "BBB").first();
        assertEquals("BBB", product.code);

        // delete
        product.delete();
        assertEquals(0, Product.count());
    }

}
