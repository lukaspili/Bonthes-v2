import models.products.Family;
import models.products.Tva;
import org.junit.Test;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
public class TvaTest extends BaseUnitTest {

    private long count = 2;

    @Test
    public void crud() {

        // add
        assertEquals(count, Tva.count());

        // read
        Tva entity = Tva.find("label = ?", "TVA Test").first();
        assertEquals("TVA Test", entity.label);

        // update
        entity.label = "TVA Test 2";
        entity.save();

        entity = Tva.find("label = ?", "TVA Test 2").first();
        assertEquals("TVA Test 2", entity.label);

        // delete
        entity.delete();
        assertEquals(--count, Tva.count());
    }
}
