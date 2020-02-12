import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test_Git {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testSampleCase() {
        driver.get(baseUrl);
        driver.findElement(By.name("search")).clear();
        driver.findElement(By.name("search")).sendKeys("java шилдт");
        driver.findElement(By.name("search")).sendKeys(Keys.ENTER);
		
        //кнопка в корзину
        wait.withTimeout(Duration.ofSeconds(30L))
                .pollingEvery(Duration.ofSeconds(1L))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[qa-id='tile-buy-button'] div")));
        driver.findElement(By.cssSelector("button[qa-id='tile-buy-button'] div")).click();
        
		//кнопка удалить из корзины
        driver.findElement(By.cssSelector("a[data-widget='cart']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.b1m7.b1n3 div.b1n8 span:nth-child(2)")));
        driver.findElement(By.cssSelector("div.b1m7.b1n3 div.b1n8 span:nth-child(2)")).click();
        driver.findElement(By.cssSelector("section div.g3.g4 div button div")).click();

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
