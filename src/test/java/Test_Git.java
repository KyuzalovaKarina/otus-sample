import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class Test_Git {
    private WebDriver driver;
    WebDriverWait wait;
    private String baseUrl = "https://www.ozon.ru/";
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass
    public static void setUpClass()
    {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(opt);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);
    }

    @Test
    public void testSampleCase() {
        driver.get(baseUrl);
        driver.findElement(By.name("search")).clear();
        driver.findElement(By.name("search")).sendKeys("java шилдт");
        driver.findElement(By.name("search")).sendKeys(Keys.ENTER);
        //кнопка в корзину
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'Java. Полное руководство')]")));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Компьютерные технологии')]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[a[contains(text(), 'Java. Полное руководство')]]//div[@class='ui-aa5' and contains(text(), 'В корзину')]"))).click();
//        driver.findElement(By.xpath("//div[@class='ui-aa5'][contains(text(), 'В корзину')]")).click();
        driver.findElement(By.xpath("//a[@href='/cart']")).click();
        //кнопка удалить из корзины
      
        driver.findElement(By.xpath("//span[contains(text(), 'Удалить выбранные')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class = 'b9x9'  and contains(text(), 'Удалить')]"))).click();
//        driver.findElement(By.xpath("//div[@class='ui-aa5'][contains(text(), 'Удалить')]")).click();

    }

    @After
    public void tearDown() {
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

