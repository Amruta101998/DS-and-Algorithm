// src/main/java/SeleniumExample.java
public class SeleniumExample {
    public static void main(String[] args) {
        // Initialize WebDriver
        WebDriver driver = WebDriverSetup.initializeChromeDriver();

        // Navigate to the desired website
        driver.get("https://www.google.com");

        // Perform a search using WebInteraction class
        WebInteraction.performSearch(driver, "Selenium WebDriver");

        // Wait for a few seconds to see the search results
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click on the first search result
        WebInteraction.clickOnResult(driver, 1);

        // Wait for a few seconds to see the result page
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Create a SearchResultsPage instance and get the number of results
        SearchResultsPage resultsPage = new SearchResultsPage(driver);
        int numberOfResults = resultsPage.getNumberOfResults();
        System.out.println("Number of search results: " + numberOfResults);

         // Create a SearchResultsPage instance and get the number of results
         SearchResultsPage resultsPage = new SearchResultsPage(driver);
         int numberOfResults = resultsPage.getNumberOfResults();
         System.out.println("Number of search results: " + numberOfResults);

        // Close the browser
        driver.quit();
    }
}
