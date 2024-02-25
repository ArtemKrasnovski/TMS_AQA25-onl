import org.testng.Assert;
import org.testng.annotations.Test;

public class DependencyTest {
    @Test
    public void stepb() {
        System.out.println("stepb...");
    }

    @Test (dependsOnMethods = "stepb")
    public void stepa2() {
        System.out.println("stepa2...");
    }

    @Test (dependsOnMethods = "stepa2")
    public void stepa11() {
        System.out.println("stepa11...");
        Assert.fail();
    }

    @Test (dependsOnMethods = "stepa2")
    public void stepd() {
        System.out.println("stepd...");
    }

    @Test (dependsOnMethods = {"stepa11", "stepd"}, alwaysRun = true)
    public void stepc() {
        System.out.println("stepc...");
    }
}
