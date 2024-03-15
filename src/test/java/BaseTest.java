import org.testng.annotations.*;

@Listeners(Listener.class)

public class BaseTest {

    protected Calculator calculator;

    @BeforeSuite
    public void beforeSuite() {
        calculator = new Calculator();
        System.out.println("new Calculator ok");
    }
}