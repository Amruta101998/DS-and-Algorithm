// SeleniumExample.java
public class SeleniumExample {
    private static final String API_KEY = "1234567890abcdef1234567890abcdef"; // Placeholder for an API key

    public static void main(String[] args) {
        // Initialize WebDriver
        WebDriver driver;
        try {
            driver = WebDriverSetup.initializeChromeDriver();
        } catch (WebDriverException e) {
            System.err.println("Failed to initialize Chrome driver: " + e.getMessage());
            return;
        }

        // Example of using an API key (in a real scenario, this should be done securely)
        String apiUrl = "https://api.example.com/endpoint?apikey=" + API_KEY;
        System.out.println("Accessing API with URL: " + apiUrl);

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
