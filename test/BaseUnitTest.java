import org.junit.Before;
import play.test.Fixtures;
import play.test.UnitTest;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
public class BaseUnitTest extends UnitTest {

    @Before
    public void setup() {
        Fixtures.deleteAllModels();
        Fixtures.loadModels("data.yml");
    }
}
