import org.junit.Before;
import play.test.Fixtures;
import play.test.FunctionalTest;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
public class BaseFunctionalTest extends FunctionalTest {

    @Before
    public void setup() {
        Fixtures.deleteAllModels();
        Fixtures.loadModels("data.yml");
    }
}
