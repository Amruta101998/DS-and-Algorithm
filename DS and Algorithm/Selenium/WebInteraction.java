// src/main/java/WebInteraction.java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebInteraction {
    public static void performSearch(WebDriver driver, String searchTerm) {
        WebElement searchBox = driver.findElement(By.cssSelector("input[name='q']"));
        searchBox.sendKeys(searchTerm);

        WebElement searchButton = driver.findElement(By.cssSelector("input[type='submit']"));
        searchButton.click();
    }
}
