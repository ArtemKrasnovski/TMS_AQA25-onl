import org.testng.annotations.BeforeClass;

public class BaseClassTest extends BaseTest {
    @BeforeClass
    public void beforeClass() {
        System.out.println("BaseClassTest... ");
    }
}