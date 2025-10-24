// src/main/java/WebDriverSetup.java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSetup {
    public static WebDriver initializeChromeDriver() {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER_PATH"));
        return new ChromeDriver();
    }
}
