import core.BrowserService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SmokeSKFTest extends BaseTest {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new BrowserService().getDriver();
    }

    @Test
    public void validateSKF() throws InterruptedException {
        driver.get("https://bymed.top/calc/%D1%81%D0%BA%D1%84-2148");

        Thread.sleep(1000);

        driver.switchTo().frame(1);
        WebElement age = driver.findElement(By.id("age"));
        age.sendKeys("33");

        WebElement sex = driver.findElement(By.id("sex"));
        Select sexDropdown = new Select(sex);
        sexDropdown.selectByIndex(0);

        WebElement creatinineList = driver.findElement(By.id("cr-size"));
        Select creatinineDropdown = new Select(creatinineList);
        creatinineDropdown.selectByIndex(2);
        WebElement creatinine = driver.findElement(By.id("cr"));
        creatinine.sendKeys("0.98");

        WebElement race = driver.findElement(By.id("race"));
        Select raceDropdown = new Select(race);
        raceDropdown.selectByIndex(0);

        WebElement weight = driver.findElement(By.id("mass"));
        weight.sendKeys("90");

        WebElement height = driver.findElement(By.id("grow"));
        height.sendKeys("183");

//      WebElement button = driver.findElement(By...("Рассчитать")); // не понятен локатор для поиска, получается только XPath
        WebElement button = driver.findElement(By.xpath("//button[text()='Рассчитать']"));
        button.click();

        WebElement mdrdResult = driver.findElement(By.id("mdrd"));
        WebElement ckdepiResult = driver.findElement(By.id("ckd_epi"));
        WebElement cgeResult = driver.findElement(By.id("cge"));

        Assert.assertEquals(mdrdResult.getText().trim(), "88.08 мл/мин/1.73м2 - Незначительно сниженный уровень СКФ (C2)");
        Assert.assertEquals(ckdepiResult.getText().trim(), "100.89 мл/мин/1.73м2 - Нормальный уровень СКФ (C1)");
        Assert.assertEquals(cgeResult.getText().trim(), "136.48 мл/мин");

        Thread.sleep(2000); //просмотр результата
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
