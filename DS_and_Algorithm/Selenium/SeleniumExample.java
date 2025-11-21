// SeleniumExample.java
import java.lang.System;
import java.util.Map;
public class SeleniumExample {
    private static final String API_KEY = System.getenv("API_KEY");

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
        // Example of using the API key to authenticate a request
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(apiUrl)
                .header("Authorization", "Bearer " + API_KEY)
                .build();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        logger.debug("API Response received with status: " + response.code());
        String apiUrl = System.getenv("API_ENDPOINT_URL");
        logger.debug("Accessing API endpoint")

        // Navigate to the desired website
        driver.get(System.getenv("APP_URL"));

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
