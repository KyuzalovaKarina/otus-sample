import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class Test_Git {
    private WebDriver driver;
    WebDriverWait wait;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass
    public static void setUpClass()
    {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void testSampleCase() throws Exception {
        driver.get("https://www.ozon.ru/");
        driver.findElement(By.name("search")).clear();
        driver.findElement(By.name("search")).sendKeys("java шилдт");
        driver.findElement(By.cssSelector("svg.ui-ai2")).click();
        driver.findElement(By.cssSelector("svg.ui-ai2")).click();
        //кнопка в корзину
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ui-aa5'][contains(text(), 'В корзину')]")));
        driver.findElement(By.xpath("//div[@class='ui-aa5'][contains(text(), 'В корзину')]")).click();
        driver.findElement(By.cssSelector("[data-widget='cart'] .f-caption--bold")).click();
        //кнопка удалить из корзины
        driver.findElement(By.xpath("//span[contains(text(), 'Удалить выбранные')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ui-aa5'][contains(text(), 'Удалить')]")));
        driver.findElement(By.xpath("//div[@class='ui-aa5'][contains(text(), 'Удалить')]")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
