import org.testng.annotations.*;

public class BaseTest {
    protected static int counter = 0;
    protected Calculator calculator = new Calculator();

    @BeforeSuite
    public void beforeSuite() {
        counter++;
        System.out.println("BeforeSuite: ..." + counter);
    }

    @BeforeTest
    public void beforeTest() {
        counter++;
        System.out.println("BeforeTest: ..." + counter);
    }

    @BeforeClass
    public void beforeClass() {
        counter++;
        System.out.println("BeforeClass: ..." + counter);
    }

    @BeforeMethod
    public void beforeMethod() {
        counter++;
        System.out.println("BeforeMethod: ..." + counter);
    }

    @BeforeGroups
    public void beforeGroups() {
        counter++;
        System.out.println("BeforeGroups: ..." + counter);
    }

    @AfterSuite
    public void afterSuite() {
        counter++;
        System.out.println("AfterSuite: ..." + counter);
    }

    @AfterTest
    public void afterTest() {
        counter++;
        System.out.println("AfterTest: ..." + counter);
    }

    @AfterClass
    public void afterClass() {
        counter++;
        System.out.println("AfterClass: ..." + counter);
    }

    @AfterMethod
    public void afterMethod() {
        counter++;
        System.out.println("AfterMethod: ..." + counter);
    }

    @AfterGroups
    public void afterGroups() {
        counter++;
        System.out.println("AfterGroups: ..." + counter);
    }
}