import models.products.Family;
import models.products.Product;
import models.products.Tea;
import org.junit.Test;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
public class FamilyTest extends BaseUnitTest {

    private long count = 2;

    @Test
    public void crud() {

        // add
        assertEquals(count, Family.count());

        // read
        Family entity = Family.find("value = ?", "family_test").first();
        assertEquals("family_test", entity.value);

        // update
        entity.value = "family_test_edit";
        entity.save();

        entity = Family.find("value = ?", "family_test_edit").first();
        assertEquals("family_test_edit", entity.value);

        // delete
        entity.delete();
        assertEquals(--count, Family.count());
    }
}
