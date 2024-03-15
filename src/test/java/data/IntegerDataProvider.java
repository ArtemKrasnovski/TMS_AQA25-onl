package data;

import org.testng.annotations.DataProvider;

public class IntegerDataProvider {

    @DataProvider(name = "IntegerDataProvider", parallel = true)
    public static Object[][] dataForIntDiv() {
        return new Object[][]{
                {-9, -3, 3},
                {30, 2, 15},
                {100, 100, 1}
        };
    }
}