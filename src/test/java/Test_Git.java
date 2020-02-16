import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;


public class Test_Git {
    private WebDriver driver; //driver initialization
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {


        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

    }

    @Test
    public void testSampleCase() throws Exception {
        driver.get("https://www.ozon.ru/"); //open page
        driver.findElement(By.name("search")).clear(); //field clearing
        driver.findElement(By.name("search")).sendKeys("java шилдт");
        driver.findElement(By.cssSelector("svg.ui-ai2")).click();
        driver.findElement(By.cssSelector("svg.ui-ai2")).click();

        //кнопка в корзину


        if (isElementPresent(By.xpath(".//div[.//p[contains(text(),'Используя сайт OZON, вы соглашаетесь с использованием файлов cookie')]]")))
            driver.findElement(By.xpath(".//button[@aria-label='Закрыть сообщение']")).click();
        driver.findElement(By.xpath(".//div[contains(text(),'В корзину')]")).click(); // Fixed the locator here (Locator for the first element in the list)
        driver.findElement(By.xpath(".//a[.//span[contains(text(), 'Корзина')]]")).click();
//        кнопка удалить из корзины
        WebDriverWait wait = new WebDriverWait(driver, 50L);
        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath(".//span[contains(text(),'Удалить выбранные')]")));
        element.click();
        if (isElementPresent(By.xpath(".//div[@data-test-id = 'modal-container']")))
            driver.findElement(By.xpath(".//button[.//div[contains(text(),'Удалить')]]")).click();

    }

    @After
    public void tearDown() throws Exception {
        driver.quit(); //
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
