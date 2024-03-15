package data;

import org.testng.annotations.DataProvider;

public class DoubleDataProvider {

    @DataProvider(name = "DoubleDataProvider", parallel = true)
    public static Object[][] dataForDoubleDiv() {
        return new Object[][]{
                {5.0, 2.0, 2.5},
                {8.0, 5.0, 1.6},
                {-50.0, 4.0, -12.5}
        };
    }
}