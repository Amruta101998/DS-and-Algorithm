// WebDriverSetup.java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSetup {
    public static WebDriver initializeChromeDriver() {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        
        // Create a new instance of the ChromeDriver
        return new ChromeDriver();
    }
}
