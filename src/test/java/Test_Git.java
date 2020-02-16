import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test_Git {
    private WebDriver driver; //driver initialization
    WebDriverWait wait;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);
    }

    @Test
    public void testSampleCase() throws Exception {

        driver.get("https://www.ozon.ru/");
        driver.findElement(By.xpath("(//input[@name='search'])")).clear();
        driver.findElement(By.name("search")).sendKeys("java шилдт");
        driver.findElement(By.cssSelector("svg.ui-ai2")).click();
        driver.findElement(By.cssSelector("svg.ui-ai2")).click();

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

