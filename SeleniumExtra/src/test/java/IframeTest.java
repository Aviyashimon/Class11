import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class IframeTest {
    private static WebDriver driver;

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", Constants.CHROMEDRIVER_PATH);
        driver = new ChromeDriver();
        driver.get("https://dgotlieb.github.io/Selenium-Extra/iFrames.html");
    }

    @Test
    public void test01_pressAlert() throws InterruptedException {
        driver.findElement(By.id("alert")).click();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
    }

    @Test
    public void test02_tryIframeBeforeSwitching() {
        System.out.println("We know it will fail...");
        driver.findElement(By.id("iframe_alert")).click();
    }

    @Test
    public void test03_switchToIframe() {
        System.out.println("Switching to iFrame...");
        driver.switchTo().frame("my_frame");
        driver.findElement(By.id("iframe_alert")).click();
        driver.switchTo().alert().accept();
    }

    @Test
    public void test03_switchBack() throws InterruptedException {
        System.out.println("Switching back...");
        driver.switchTo().defaultContent();
        driver.findElement(By.id("alert")).click();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
    }

    // todo //////////////////////////////////////////////////////////////////////////////////
    // todo //// Thread.sleep is bad practice and is here only so we can watch the actions ///
    // todo //////////////////////////////////////////////////////////////////////////////////

    @AfterClass
    public static void afterClass() {
        driver.quit();
    }
}
