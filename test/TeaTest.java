import models.products.Product;
import models.products.Tea;
import org.junit.*;

public class TeaTest extends BaseUnitTest {

    private long count = 1;

    @Test
    public void crud() {

        // add
        assertEquals(count, Tea.count());

        // read
        Tea entity = Tea.find("code = ?", "TEST").first();
        assertEquals("TEST", entity.code);

        // family
        assertEquals("family_tea", entity.family.value);

        // tva
        assertEquals("TVA Tea", entity.tva.label);

        // update
        entity.code = "TEST_EDIT";
        entity.save();

        entity = Tea.find("code = ?", "TEST_EDIT").first();
        assertEquals("TEST_EDIT", entity.code);

        // delete
        entity.delete();
        assertEquals(--count, Product.count());
    }

}
