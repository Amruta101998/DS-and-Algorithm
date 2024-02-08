// WebInteraction.java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebInteraction {
    public static void performSearch(WebDriver driver, String searchTerm) {
        // Find an element by its CSS selector and interact with it
        WebElement searchBox = driver.findElement(By.cssSelector("input[name='q']"));
        searchBox.sendKeys(searchTerm);

        // Find and click the search button
        WebElement searchButton = driver.findElement(By.cssSelector("input[type='submit']"));
        searchButton.click();
    }
}
