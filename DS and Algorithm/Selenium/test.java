//simple example that opens a browser, navigates to a website, and interacts with a web page

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumExample {
    public static void main(String[] args) {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the desired website
        driver.get("https://www.example.com");

        // Find an element by its CSS selector and interact with it
        WebElement searchBox = driver.findElement(By.cssSelector("input[name='q']"));
        searchBox.sendKeys("Selenium WebDriver");

        // Find and click the search button
        WebElement searchButton = driver.findElement(By.cssSelector("input[type='submit']"));
        searchButton.click();

        // Wait for a few seconds to see the result
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Close the browser
        driver.quit();
    }
}
