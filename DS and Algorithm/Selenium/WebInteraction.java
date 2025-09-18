// src/main/java/WebInteraction.java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class WebInteraction {
    public static void performSearch(WebDriver driver, String searchTerm) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='q']")));
        searchBox.sendKeys(searchTerm);

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='submit']")));
        searchButton.click();
    }
}
