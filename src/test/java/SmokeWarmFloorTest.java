import core.BrowserService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SmokeWarmFloorTest extends BaseTest {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new BrowserService().getDriver();
    }

    @Test
    public void validateWarmFloor() throws InterruptedException {
        driver.get("https://kermi-fko.ru/raschety/Calc-Rehau-Solelec.aspx");
        Thread.sleep(1000);

        WebElement floorWidth = driver.findElement(By.id("el_f_width"));
        floorWidth.sendKeys("6");

        WebElement floorLength = driver.findElement(By.id("el_f_lenght"));
        floorLength.sendKeys("6");

        WebElement roomType = driver.findElement(By.id("room_type"));
        Select roomTypeDropdown = new Select(roomType);
        roomTypeDropdown.selectByIndex(1);

        WebElement info = driver.findElement(By.className("helptip"));
        info.click();

        WebElement heatingType = driver.findElement(By.id("heating_type"));
        Select heatingTypeDropdown = new Select(heatingType);
        heatingTypeDropdown.selectByIndex(2);

        WebElement floorLosses = driver.findElement(By.id("el_f_losses"));
        floorLosses.sendKeys("1500");

        WebElement calcButton = driver.findElement(By.name("button"));
        calcButton.click();

        WebElement power = driver.findElement(By.id("floor_cable_power"));
        WebElement powerDensity = driver.findElement(By.id("spec_floor_cable_power"));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(power.getAttribute("value"), "835", "Calculation error!");
        softAssert.assertEquals(powerDensity.getAttribute("value"), "23", "Calculation error!");
        softAssert.assertAll();

        Thread.sleep(2000); //просмотр результата
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
