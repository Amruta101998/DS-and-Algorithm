// SeleniumExample.java
public class SeleniumExample {
    public static void main(String[] args) {
        // Initialize Web Driver
        WebDriver driver = WebDriverSetup.initializeChromeDriver();

        // Navigate to the desired website
        driver.get("https://www.example.com");

        // Perform a search using WebInteraction class
        
        WebInteraction.performSearch(driver, "Selenium WebDriver");

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
