import core.BrowserService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SmokeLaminateTest extends BaseTest {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new BrowserService().getDriver();
    }

    @Test(retryAnalyzer = Retry.class)
    //retry, т.к. не всегда получается корректно убрать капчу
    // и бывает в конце при рассчетах вылетает ошибка загрузки данных с сервера
    public void validateLaminate() throws InterruptedException {
        driver.get("https://calc.by/building-calculators/laminate.html");

        Thread.sleep(15000); //убрать капчу, если проявляется

        WebElement roomLength = driver.findElement(By.id("ln_room_id"));
        roomLength.clear();
        roomLength.sendKeys("600");

        WebElement roomWidth = driver.findElement(By.id("wd_room_id"));
        roomWidth.clear();
        roomWidth.sendKeys("600");

        WebElement lengthLaminate = driver.findElement(By.id("ln_lam_id"));
        lengthLaminate.clear();
        lengthLaminate.sendKeys("1291");

        WebElement widthLaminate = driver.findElement(By.id("wd_lam_id"));
        widthLaminate.clear();
        widthLaminate.sendKeys("193");

        WebElement piecesInPackage = driver.findElement(By.id("n_packing"));
        piecesInPackage.clear();
        piecesInPackage.sendKeys("10");

        WebElement layingLaminate = driver.findElement(By.id("laying_method_laminate"));
        Select layingDropdown = new Select(layingLaminate);
        layingDropdown.selectByValue("1");

        WebElement distanceFromWalls = driver.findElement(By.id("indent_walls_id"));
        distanceFromWalls.clear();
        distanceFromWalls.sendKeys("9");

        WebElement directionLaminate = driver.findElement(By.id("direction-laminate-id2"));
        directionLaminate.click();

        WebElement calculatorButton = driver.findElement(By.className("calc-btn-div"));
        calculatorButton.click();

        Thread.sleep(3000);

        WebElement calculatePlankAmount = driver.findElement(By.xpath("//*[@id=\"t3-content\"]/div[3]/article/section/div[2]/div[3]/div[2]/div[1]"));
        WebElement calculatePackAmount = driver.findElement(By.xpath("//*[@id=\"t3-content\"]/div[3]/article/section/div[2]/div[3]/div[2]/div[2]"));
        Assert.assertEquals(calculatePlankAmount.getText(), "Требуемое количество досок ламината: 162");
        Assert.assertEquals(calculatePackAmount.getText(), "Количество упаковок ламината: 17");

        Thread.sleep(2000); //просмотреть результаты
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
