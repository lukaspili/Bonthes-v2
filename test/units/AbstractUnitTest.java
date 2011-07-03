package units;

import org.junit.After;
import org.junit.Before;
import play.test.Fixtures;
import play.test.UnitTest;

/**
 * @author Lukasz Piliszczuk <lukasz.piliszczuk AT zenika.com>
 */
public abstract class AbstractUnitTest extends UnitTest {

    @Before
    public void before() {
        Fixtures.loadModels("data.yml");
    }

    @After
    public void after() {
        Fixtures.deleteAllModels();
    }
}
