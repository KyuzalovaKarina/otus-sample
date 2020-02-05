import java.util.concurrent.TimeUnit;
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
        System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testSampleCase() throws Exception {
        driver.get("https://www.ozon.ru/");
        driver.findElement(By.name("search")).clear();
        driver.findElement(By.name("search")).sendKeys("java шилдт");
        driver.findElement(By.cssSelector("svg.ui-ai2")).click();
        driver.findElement(By.cssSelector("svg.ui-ai2")).click();
        //кнопка в корзину
        driver.findElement(By.xpath("//div[@id='__layout']/div/div/div[2]/div[2]/div[2]/div[2]/div/div/div/div/div/div/div[3]/div/button")).click();
        driver.findElement(By.cssSelector("svg.a8n")).click();
        //кнопка удалить из корзины
        driver.findElement(By.xpath("//div[@id='__layout']/div/div/div/div/div[3]/div[5]/div/div/div/div[2]/div[3]/div[2]/div[2]/span[2]")).click();
        driver.findElement(By.xpath("//div[3]/button/div")).click();
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
