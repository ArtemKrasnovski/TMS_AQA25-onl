import data.StaticProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.beans.ExceptionListener;
import java.util.List;
import java.util.Random;

public class CalcTest extends BaseClassTest {
    private int count = 0;

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("CalcTest started...");
    }

    @Test
    public void testSum() {
        Assert.assertEquals(calculator.sum(2, 3), 5, "Неверная сумма двух чисел");
        //Assert.assertEquals(calculator.sum(2, 3), 5);
        counter++;
    }

    @Test(enabled = false)
    public void testSum1() {
        Assert.assertEquals(calculator.sum(2, 3), 5, "Неверная сумма двух чисел");
    }

    @Test(description = "Тест с описанием")
    public void testDescription() {
        Assert.assertEquals(calculator.sum(2, 3), 5, "Неверная сумма двух чисел");
    }

    @Test(testName = "Переименнованный тест")
    public void testName() {
        Assert.assertEquals(calculator.sum(2, 3), 5, "Неверная сумма двух чисел");
    }

    @Test(timeOut = 1000)
    public void testTimeout() throws InterruptedException {
        Thread.sleep(500);
    }

    @Test(invocationCount = 3, threadPoolSize = 3, invocationTimeOut = 1000)
    //@Test(invocationCount = 3, invocationTimeOut = 1000)
    public void invocationCountTest() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println(count++);
    }

    @Test(dataProvider = "dataForSum", dataProviderClass = StaticProvider.class, threadPoolSize = 3)
    public void testDataProvider(int a, int b, int expectedResult) throws InterruptedException {
        //Thread.sleep(new Random().nextInt(1000));
        Thread.sleep(500);
        Assert.assertEquals(calculator.sum(a, b), expectedResult, "Неверная сумма двух чисел");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testExceptions() {
        List list = null;
        int size = list.size();
    }
}
