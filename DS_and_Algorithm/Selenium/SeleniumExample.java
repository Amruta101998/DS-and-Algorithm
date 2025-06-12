// SeleniumExample.java
public class SeleniumExample {
    public static void main(String[] args) {
        // Initialize WebDriver
        WebDriver driver;
        try {
            driver = WebDriverSetup.initializeChromeDriver();
        } catch (WebDriverException e) {
            System.err.println("Failed to initialize Chrome driver: " + e.getMessage());
            return;
        }

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
