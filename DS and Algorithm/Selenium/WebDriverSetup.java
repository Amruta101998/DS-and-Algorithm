// src/main/java/WebDriverSetup.java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSetup {
    private static final String DEFAULT_CHROME_DRIVER_PATH = "lib/chromedriver.exe";

    public static WebDriver initializeChromeDriver() {
        String driverPath = System.getProperty("webdriver.chrome.driver.path", DEFAULT_CHROME_DRIVER_PATH);
        System.setProperty("webdriver.chrome.driver", driverPath);
        return new ChromeDriver();
    }
}
