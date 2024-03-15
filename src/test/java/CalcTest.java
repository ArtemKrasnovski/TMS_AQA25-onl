import data.DoubleDataProvider;
import data.IntegerDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CalcTest extends BaseTest {

    @Test(testName = "Division of integer numbers", description = "Деление чисел типа Integer",
            groups = "Positive", timeOut = 1000, priority = 1)
    public void testIntDiv() throws InterruptedException {
        Assert.assertEquals(calculator.div(6, 3), 2, "Неверный результат деления двух чисел");
        Thread.sleep(900);
    }

    @Test(testName = "Division of integer numbers_Negative", enabled = false, groups = "Negative")
    public void testDisableIntDiv() {
        Assert.assertEquals(calculator.div(10, 3), 5, "Неверный результат деления двух чисел");
    }

    @Test(testName = "Division of integer numbers, DataProvider", dataProvider = "IntegerDataProvider",
            dataProviderClass = IntegerDataProvider.class, invocationCount = 2, invocationTimeOut = 1000,
            threadPoolSize = 2, groups = "Positive", dependsOnMethods = "testIntDiv", alwaysRun = true)
    public void testIntDivDataProvider(int a, int b, int expectedResult) {
        Assert.assertEquals(calculator.div(a, b), expectedResult, "Неверный результат деления двух чисел");
    }

    @Test(testName = "Division of double numbers", description = "Деление чисел типа Double",
            groups = "Positive", timeOut = 1000, priority = 2)
    public void testDoubleDiv() {
        Assert.assertEquals(calculator.div(10.0, 8.0), 1.25, "Неверный результат деления двух чисел");
    }

    @Test(testName = "Division of double numbers_Negative", enabled = false, groups = "Negative")
    public void testDisableDoubleDiv() {
        Assert.assertEquals(calculator.div(20.0, 2.0), 5.0, "Неверный результат деления двух чисел");
    }

    @Test(testName = "Division of double numbers, DataProvider", dataProvider = "DoubleDataProvider",
            dataProviderClass = DoubleDataProvider.class, invocationCount = 2, invocationTimeOut = 1000,
            threadPoolSize = 2, groups = "Positive", dependsOnMethods = "testDoubleDiv")
    public void testDoubleDivDataProvider(double a, double b, double expectedResult) {
        Assert.assertEquals(calculator.div(a, b), expectedResult, "Неверный результат деления двух чисел");
    }

    @Test(testName = "Division Int by Zero", expectedExceptions = ArithmeticException.class,
            dependsOnMethods = "testIntDiv", priority = 3, alwaysRun = true)
    public void testDivByZero() {
        calculator.div(10, 0);
    }

    @Test(testName = "Division Double by Zero", priority = 4)
    public void testDoubleDivByZero() {
        Assert.assertEquals(Double.POSITIVE_INFINITY, calculator.div(20.50, 0), 0.0001);
    }

    @Test(testName = "Division Double by Zero_Negative", priority = 5)
    public void testNegativeDoubleDivByZero() {
        Assert.assertEquals(Double.NEGATIVE_INFINITY, calculator.div(-10.50, 0), 0.0001);
    }

    @Test(testName = "Division Zero by Zero", priority = 6)
    public void testNaN() {
        Assert.assertEquals(Double.NaN, calculator.div(0.00, 0.00), 0.0001);
    }

    @Test(testName = "Test with retryAnalyzer", retryAnalyzer = Retry.class, priority = 7)
    public void TestRetryAnalyzer() {
        Assert.assertEquals(calculator.div(100, 25), 4);
    }
}
